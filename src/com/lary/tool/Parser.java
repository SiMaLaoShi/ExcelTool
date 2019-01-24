package com.lary.tool;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Parser {

    public static DecimalFormat decimalFormat = new DecimalFormat(".00");
    private static ArrayList<Integer> ignoreList = new ArrayList<>();
    private static ArrayList<Integer> svrignoreList = new ArrayList<>();

    // 把Excel表里的
    public static void parseExcel(File excelFile)
            throws FileNotFoundException, IOException {
        Workbook book = new XSSFWorkbook(new FileInputStream(excelFile));

        int sheetNum = book.getNumberOfSheets();
        for (int i = 0; i < sheetNum; i++) {
            Sheet unitSheet = book.getSheetAt(i);
            String sheetName = book.getSheetName(i);
            PaserSheet(unitSheet, sheetName, excelFile);
            PaserSvrSheet(unitSheet, sheetName, excelFile);
        }
    }

    // 转换客户端表
    public static void PaserSheet(Sheet sheet, String name, File excelFile)
            throws FileNotFoundException, IOException {
        ignoreList.clear();
        ArrayList<ArrayList<String>> resultList = new ArrayList<>();
        if (name.startsWith("Sheet")) {
            System.out.println(excelFile.getName() + "未命名,请先命名!");
            return;
        }
        System.out.println(name + "开始转换");


        ArrayList<String> list = null;
        int rowNum = sheet.getLastRowNum();
        int columnCount = sheet.getRow(0).getLastCellNum();
        Row row = sheet.getRow(4);
        FilterIgnore(row, columnCount);
        fillArrayList(rowNum, sheet, row, list, resultList);

        System.out.println(String.format("读取%s完成!", new Object[]{excelFile}));
        buildLua(resultList, name, PropConf.get(PropConf.Key_ClientSavePath));
    }

    // 转换服务器表
    public static void PaserSvrSheet(Sheet sheet, String name, File excelFile)
            throws FileNotFoundException, IOException {
        svrignoreList.clear();
        ArrayList<ArrayList<String>> resultList = new ArrayList<>();
        if (name.startsWith("Sheet")) {
            System.out.println(excelFile.getName() + "未命名,请先命名!");
            return;
        }
        System.out.println(name + "开始转换");

        ArrayList<String> list = null;
        int rowNum = sheet.getLastRowNum();
        int columnCount = sheet.getRow(0).getLastCellNum();
        Row row = sheet.getRow(4);
        FilterSvrIgnore(row, columnCount);
        fillArrayList(rowNum, sheet, row, list, resultList);

        System.out.println(String.format("读取%s完成!", new Object[]{excelFile}));
        buildLua(resultList, name, PropConf.get(PropConf.Key_ServerSavePath));
    }

    // 把Excel表里面的数据填充到ArrayList
    static void fillArrayList(int rowNum, Sheet sheet, Row row, ArrayList<String> list, ArrayList<ArrayList<String>> resultList) {
        int columnCount;
        for (int rowIndex = 0; rowIndex <= rowNum; rowIndex++) {
            if ((rowIndex != 3) && (rowIndex != 4)) {
                columnCount = sheet.getRow(0).getLastCellNum();
                row = sheet.getRow(rowIndex);
                if (row != null) {
                    list = new ArrayList<>();
                    for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
                        if (!ignoreList.contains(Integer.valueOf(columnIndex))) {
                            Cell cell = row.getCell(columnIndex);

                            String cellStr = "";
                            if (cell != null) {
                                cell.setCellType(1);
                                cellStr = cell.getStringCellValue().trim();

                                "".equals(cellStr);
                            }
                            System.out.print(cellStr + "\t");
                            list.add(cellStr);
                        }
                    }
                    if (list.size() != 0) {
                        if (!"".equals(list.get(0))) {
                            resultList.add(list);
                        }
                    } else {
                        System.out.println();
                    }
                }
            }
        }
    }

    // 过滤客户端不用的数据
    public static void FilterIgnore(Row row, int columnCount) {
        if (row != null) {
            for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
                if (!ignoreList.contains(Integer.valueOf(columnIndex))) {
                    Cell cell = row.getCell(columnIndex);

                    String cellStr = "";
                    if (cell != null) {
                        cell.setCellType(1);
                        cellStr = cell.getStringCellValue().trim();
                        if (("0".equals(cellStr)) || ("2".equals(cellStr))) {
                            ignoreList.add(Integer.valueOf(columnIndex));
                        }
                    }
                }
            }
        }
    }

    // 过滤服务器不用的数据
    public static void FilterSvrIgnore(Row row, int columnCount) {
        if (row != null) {
            for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
                if (!svrignoreList.contains(Integer.valueOf(columnIndex))) {
                    Cell cell = row.getCell(columnIndex);

                    String cellStr = "";
                    if (cell != null) {
                        cell.setCellType(1);
                        cellStr = cell.getStringCellValue().trim();
                        if (("0".equals(cellStr)) || ("1".equals(cellStr))) {
                            svrignoreList.add(Integer.valueOf(columnIndex));
                        }
                    }
                }
            }
        }
    }

    // 导出Xml文件
    public static void buildXML(ArrayList<ArrayList<String>> list, String name, String path)
            throws IOException {
        ArrayList<String> strList = (ArrayList<String>) list.get(0);
        StringBuffer formatSB = new StringBuffer("\t<" + name);
        for (String attr : strList) {
            formatSB.append(" " + attr + "=\"%s\"");
        }
        formatSB.append(" />");

        File file = new File(String.format("%s\\%s.xml", new Object[]{path, name}));
        if (!file.exists()) {
            file.createNewFile();
        }
        PrintWriter out = null;
        try {
            out = new PrintWriter(file, "UTF-8");
            out.println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
            out.println("<Root>");
            for (int i = 1; i < list.size(); i++) {
                out.println(String.format(formatSB.toString(), ((ArrayList) list.get(i)).toArray()));
            }
            out.println("</Root>");
            out.flush();
            System.out.println(name + " 转xml成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("excel表配置错误，请检查！");
        } finally {
            if (out != null) {
                out.close();
                out = null;
            }
        }
    }

    // 导出lua文件
    public static void buildLua(ArrayList<ArrayList<String>> list, String name, String path)
            throws IOException {
        if (list.size() == 0) {
            return;
        }
        File file = new File(String.format("%s\\%s.lua", new Object[]{path, name}));
        if (!file.exists()) {
            file.createNewFile();
        }
        PrintWriter out = null;
        try {
            out = new PrintWriter(file, "UTF-8");
            out.println(String.format("local %s = {", new Object[]{name}));

            ArrayList<String> list0 = (ArrayList<String>) list.get(0);

            ArrayList<String> list1 = (ArrayList<String>) list.get(1);

            ArrayList<String> list2 = (ArrayList<String>) list.get(2);

            boolean isStr = ((String) list2.get(0)).equalsIgnoreCase("string");
            for (int i = 3; i < list.size(); i++) {
                out.println(String.format(isStr ? "\t['%s'] = {" : "\t[%s] = {", new Object[]{((ArrayList) list.get(i)).get(0)}));
                for (int j = 0; j < list0.size(); j++) {
                    String str = j == list0.size() - 1 ? "" : ",";
                    String type = ((String) list2.get(j)).toLowerCase();
                    // 去除默认数据
                    String nullData = list.get(i).get(j);
                    if (nullData == null || nullData.isEmpty())
                        continue;
                    if ("table".equals(type)) {
                        String tempStr = (String) ((ArrayList) list.get(i)).get(j);
                        out.print(String.format("\t\t['%s'] = %s%s", new Object[]{list0.get(j), tempStr, str}));
                    } else if ("string[]".equals(type)) {
                        out.print(String.format("\t\t['%s'] = {'%s'}%s", new Object[]{list0.get(j), ((String) ((ArrayList) list.get(i)).get(j)).replaceAll(",", "','"), str}));
                    } else if (("int[]".equals(type)) || ("float[]".equals(type))) {
                        out.print(String.format("\t\t['%s'] = {%s}%s", new Object[]{list0.get(j), ((ArrayList) list.get(i)).get(j), str}));
                    } else if ("string".equals(type)) {
                        String desc = (String) ((ArrayList) list.get(i)).get(j);
                        if (desc.contains("|")) {
                            out.print(String.format("\t\t['%s'] = {'%s'}%s", new Object[]{list0.get(j), ((String) ((ArrayList) list.get(i)).get(j)).substring(1).replaceAll("\\|", "','"), str}));
                        } else {
                            out.print(String.format("\t\t['%s'] = '%s'%s", new Object[]{list0.get(j), ((ArrayList) list.get(i)).get(j), str}));
                        }
                    } else if ("float".equals(type)) {
                        float temp = Float.parseFloat((String) ((ArrayList) list.get(i)).get(j));
                        out.print(String.format("\t\t['%s'] = %s%s", new Object[]{list0.get(j), decimalFormat.format(temp), str}));
                    } else {
                        out.print(String.format("\t\t['%s'] = %s%s", new Object[]{list0.get(j), ((ArrayList) list.get(i)).get(j), str}));
                    }
                    String exp = i == 3 ? String.format("\t--%s", new Object[]{list1.get(j)}) : "";
                    out.println(exp);
                }
                out.println("\t\t}" + (i == list.size() - 1 ? "" : ","));
            }
            out.println("\t}");

            //region 写入默认值
            out.println("local __default_values = {");
            for (int j = 0; j < list0.size(); j++) {
                System.out.println(list0.get(j));
                String str = (j == list0.size() - 1 ? "" : ",");
                String type = list2.get(j).toLowerCase();
                if ("table".equals(type) || "string[]".equals(type) || "int[]".equals(type)) {
                    out.println(String.format("	['%s'] = %s,", list0.get(j), "{}"));
                } else if ("string".equals(type)) {
                    out.println(String.format("	['%s'] = %s,", list0.get(j), "\"\""));
                } else if ("float".equals(type)) {
                    out.println(String.format("	['%s'] = %s,", list0.get(j), 1.0));
                } else if ("int".equals(type)) {
                    out.println(String.format("	['%s'] = %s,", list0.get(j), 0));
                }
            }
            out.println("}");
            //endregion

            //region 设置元表
            out.println("do");

            out.println("	local base = {");
            out.println(" 		__index = __default_values, --基类，默认值存取");
            out.println(" 		__newindex = function()");
            out.println("			error( \"Attempt to modify read-only table\" )");
            out.println("		end");
            out.println("	}");

            out.println(String.format("	for k, v in pairs( %s ) do", name));
            out.println("		setmetatable( v, base )");
            out.println("	end");
//            out.println("	base.__metatable = false --不让外面获取到元表，防止被无意修改");
            out.println("end");
            //endregion
            out.println("return " + name);
            System.out.println(name + " 转lua成功!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
                out = null;
            }
        }
    }
}
