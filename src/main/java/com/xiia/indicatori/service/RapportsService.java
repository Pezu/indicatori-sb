package com.xiia.indicatori.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.xiia.indicatori.domain.Expense;
import com.xiia.indicatori.domain.Monthly;
import com.xiia.indicatori.domain.MonthlyType;
import com.xiia.indicatori.domain.Relation;
import com.xiia.indicatori.pojo.rapports.SummaryLine;
import com.xiia.indicatori.repositories.RepositoryRegistry;

@Service
public class RapportsService {
	
	static final Integer CONT_HOSP_ID = 2;
	
	@PersistenceContext
    private EntityManager entityManager;
    private final RepositoryRegistry repositoryRegistry;
    
    public RapportsService(final RepositoryRegistry repositoryRegistry, final EntityManager entityManager) {
    	this.repositoryRegistry = repositoryRegistry;
    	this.entityManager = entityManager;
	}

	public List<SummaryLine> read(String rapport, String month) {
		
		List<SummaryLine> response = new ArrayList<SummaryLine>();
		
		Map<Integer, Double> mapPacients = new HashMap<Integer, Double>();
		Map<Integer, Double> mapDays = new HashMap<Integer, Double>();
		Map<Integer, Double> mapBeds = new HashMap<Integer, Double>();
		Map<Integer, Double> mapAmmounts = new HashMap<Integer, Double>();
		Map<Integer, String> mapMonthlyType = new HashMap<Integer, String>();
		
		Double totalBed = 0.0;
		Double totalDays = 0.0;
		Double totalPacients = 0.0;
		Double totalAmmount = 0.0;
		
		List<MonthlyType> monthlyTypeList = repositoryRegistry.getMonthlyTypeRepository().findAll();
		for (MonthlyType type : monthlyTypeList) {
			mapMonthlyType.put(type.getId(), type.getCode());
		}
		
		List<Monthly> monthlyList = repositoryRegistry.getMonthlyRepository().findAllByMonth(month);
		for (Monthly monthly : monthlyList) {
			String code = mapMonthlyType.get(monthly.getTypeId());
			switch (code) {
				case "HSD":
					mapDays.put(monthly.getUnitId(), monthly.getValue());
					totalDays += monthly.getValue();
					break;
				case "PAC":
					mapPacients.put(monthly.getUnitId(), monthly.getValue());
					totalPacients += monthly.getValue();
					break;
				case "BED":
					mapBeds.put(monthly.getUnitId(), monthly.getValue());
					totalBed += monthly.getValue();
					break;
				default:
			}
		}
		
		List<Relation> relationList = repositoryRegistry.getRelationsRepository().findAllByParentId(CONT_HOSP_ID);
		List<Integer> children = relationList.stream().map(r -> r.getChild().getId()).collect(Collectors.toList());
		
		List<Expense> expenseList = repositoryRegistry.getExpensesRepository().findAllByUnitIdIn(children);
		for (Expense expense : expenseList) {
			Double value = mapAmmounts.get(expense.getUnitId());
			if (value == null) value = 0.0;
			value += expense.getAmount();
			totalAmmount += expense.getAmount();
			mapAmmounts.put(expense.getUnitId(), value);
		}

		
		
		for (Relation relation : relationList) {
			
			Double ammPacient = mapAmmounts.get(relation.getChild().getId()) == null ||
								mapPacients.get(relation.getChild().getId()) == null ||
								mapPacients.get(relation.getChild().getId()) == 0 ? 
								0.0 : mapAmmounts.get(relation.getChild().getId()) / mapPacients.get(relation.getChild().getId()); 
			Double ammDay = mapAmmounts.get(relation.getChild().getId()) == null ||
								mapDays.get(relation.getChild().getId()) == null ||
								mapDays.get(relation.getChild().getId()) == 0 ? 
								0.0 : mapAmmounts.get(relation.getChild().getId()) / mapDays.get(relation.getChild().getId()); 
			Double ammBed = mapAmmounts.get(relation.getChild().getId()) == null ||
								mapBeds.get(relation.getChild().getId()) == null ||
								mapBeds.get(relation.getChild().getId()) == 0 ? 
								0.0 : mapAmmounts.get(relation.getChild().getId()) / mapBeds.get(relation.getChild().getId());
			Double bedUsage = mapDays.get(relation.getChild().getId()) == null ||
								mapBeds.get(relation.getChild().getId()) == null ||
								mapBeds.get(relation.getChild().getId()) == 0 ? 
								0.0 : mapDays.get(relation.getChild().getId()) / mapBeds.get(relation.getChild().getId());
			Double hspDuration = mapDays.get(relation.getChild().getId()) == null ||
								mapPacients.get(relation.getChild().getId()) == null ||
								mapPacients.get(relation.getChild().getId()) == 0 ? 
								0.0 : mapDays.get(relation.getChild().getId()) / mapPacients.get(relation.getChild().getId());
			
			SummaryLine line = new SummaryLine(relation.getChild().getCode(), 
												relation.getChild().getName(), 
												mapPacients.get(relation.getChild().getId()), 
												mapBeds.get(relation.getChild().getId()), 
												mapDays.get(relation.getChild().getId()), 
												mapAmmounts.get(relation.getChild().getId()),
												ammPacient,
												ammDay,
												ammBed,
												bedUsage,
												hspDuration,
												false);
			response.add(line);
		}
		
		Double ammPacient = totalPacients == 0 ? 0.0 : totalAmmount / totalPacients; 
		Double ammDay = totalDays == 0 ? 0.0 : totalAmmount / totalDays; 
		Double ammBed = totalBed == 0 ? 0.0 : totalAmmount / totalBed; 
		Double bedUsage = totalBed == 0 ? 0.0 : totalDays / totalBed; 
		Double hspDuration = totalPacients == 0 ? 0.0 : totalDays / totalPacients; 
				
		SummaryLine line = new SummaryLine(null, 
										"TOTAL", 
										totalPacients, 
										totalBed, 
										totalDays, 
										totalAmmount,
										ammPacient,
										ammDay,
										ammBed,
										bedUsage,
										hspDuration,
										true);
		response.add(line);
		
		return response;
	}

	public String export(String rapport) {
		return null;
	}

	public XSSFWorkbook workbook(String rapport, String month) {
		
		String sheetName = "Centralizator";
		
		XSSFWorkbook wb = new XSSFWorkbook();
		CellStyle cellStyle = wb.createCellStyle();
		cellStyle.setAlignment(HorizontalAlignment.CENTER);

		CellStyle leftAlignCellStyle = wb.createCellStyle();
		leftAlignCellStyle.setAlignment(HorizontalAlignment.LEFT);
		
		XSSFSheet sheet = wb.createSheet(sheetName);
		
		// create header
		
		XSSFRow row = sheet.createRow(0);
		
		List<String> headerNames = Arrays.asList("Cod",
													"Sectie",
													"Nr.pacienti",
													"Nr.paturi",
													"Nr.zi spit",
													"Cheltuieli totale",
													"Cost med / pac",
													"Cost med / zi spital",
													"Cost med / pat",
													"Utilizare pat",
													"Durata spit");
		
		for (int i = 0; i < headerNames.size(); i++) {
			XSSFCell cell = row.createCell(i);
			cell.setCellValue(headerNames.get(i));
		}
		
		List<SummaryLine> lines = read(rapport, month);
		XSSFCell cell = null;

		for (int i = 0; i < lines.size(); i++) {
			SummaryLine line = lines.get(i);
			row = sheet.createRow(i + 1);
			
			cell = row.createCell(0);
			cell.setCellValue(line.getCode());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(1);
			cell.setCellValue(line.getName());
			cell.setCellStyle(leftAlignCellStyle);

			cell = row.createCell(2);
			cell.setCellValue(round(line.getPacients()));
			cell.setCellStyle(cellStyle);
			
			cell = row.createCell(3);
			cell.setCellValue(round(line.getBeds()));
			cell.setCellStyle(cellStyle);
			
			cell = row.createCell(4);
			cell.setCellValue(round(line.getDays()));
			cell.setCellStyle(cellStyle);
			
			cell = row.createCell(5);
			cell.setCellValue(round(line.getAmmount()));
			cell.setCellStyle(cellStyle);
			
			cell = row.createCell(6);
			cell.setCellValue(round(line.getAmmPacient()));
			cell.setCellStyle(cellStyle);
			
			cell = row.createCell(7);
			cell.setCellValue(round(line.getAmmDay()));
			cell.setCellStyle(cellStyle);
			
			cell = row.createCell(8);
			cell.setCellValue(round(line.getAmmBed()));
			cell.setCellStyle(cellStyle);
			
			cell = row.createCell(9);
			cell.setCellValue(round(line.getBedUsage()));
			cell.setCellStyle(cellStyle);
			
			cell = row.createCell(10);
			cell.setCellValue(round(line.getHspDuration()));
			cell.setCellStyle(cellStyle);

		}
		
		for (int i = 0; i < 11; i++) sheet.autoSizeColumn(i);
		
		return wb;
		
	}
	
	Double round(Double d) {
		return ((double)Math.round(d * 100)) / 100;
	}

}
