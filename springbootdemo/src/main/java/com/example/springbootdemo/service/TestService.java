package com.example.springbootdemo.service;

import com.example.springbootdemo.config.BaseFormMap;
import com.example.springbootdemo.dao.TestDao;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TestService {

    @Autowired
    private TestDao testDao;

    public int selectCount(){
        return testDao.selectCount();
    }


    public void becomeExcel() throws Exception {
        boolean result=false;
        List<BaseFormMap> dataList=new ArrayList<BaseFormMap>();//数据
        List<String> fldNameArr = new ArrayList<String>();// 字段名
        List<String> titleArr = new ArrayList<String>();// 标题
        BaseFormMap baseFormMap=new BaseFormMap();
        List<String> showtailArr=new ArrayList<String>();
        List<String> ispercentArr=new ArrayList<String>();
        baseFormMap.put("value1", "股票");
        baseFormMap.put("value2", new BigDecimal(new java.util.Random().nextDouble()));
        baseFormMap.put("value3", new BigDecimal(new java.util.Random().nextDouble()));
        baseFormMap.put("value4", new BigDecimal(new java.util.Random().nextDouble()));

        BaseFormMap baseFormMap1=new BaseFormMap();
        baseFormMap1.put("value1", "货币型基金");
        baseFormMap1.put("value2", new BigDecimal(new java.util.Random().nextDouble()));
        baseFormMap1.put("value3", new BigDecimal(new java.util.Random().nextDouble()));
        baseFormMap1.put("value4", new BigDecimal(new java.util.Random().nextDouble()));

        BaseFormMap baseFormMap2=new BaseFormMap();
        baseFormMap2.put("value1", "可转债");
        baseFormMap2.put("value2", new BigDecimal(new java.util.Random().nextDouble()));
        baseFormMap2.put("value3", new BigDecimal(new java.util.Random().nextDouble()));
        baseFormMap2.put("value4", new BigDecimal(new java.util.Random().nextDouble()));

        BaseFormMap baseFormMap3=new BaseFormMap();
        baseFormMap3.put("value1", "买入返售");
        baseFormMap3.put("value2", new BigDecimal(new java.util.Random().nextDouble()));
        baseFormMap3.put("value3", new BigDecimal(new java.util.Random().nextDouble()));
        baseFormMap3.put("value4", new BigDecimal(new java.util.Random().nextDouble()));

        BaseFormMap baseFormMap4=new BaseFormMap();
        baseFormMap4.put("value1", "通知存款");
        baseFormMap4.put("value2", new BigDecimal(new java.util.Random().nextDouble()));
        baseFormMap4.put("value3", new BigDecimal(new java.util.Random().nextDouble()));
        baseFormMap4.put("value4", new BigDecimal(new java.util.Random().nextDouble()));

        BaseFormMap baseFormMap5=new BaseFormMap();
        baseFormMap5.put("value1", "当月累计");
        baseFormMap5.put("value2", new BigDecimal(new java.util.Random().nextDouble()));
        baseFormMap5.put("value3", new BigDecimal(new java.util.Random().nextDouble()));
        baseFormMap5.put("value4", new BigDecimal(new java.util.Random().nextDouble()));

        fldNameArr.add("value1");
        fldNameArr.add("value2");
        fldNameArr.add("value3");
        fldNameArr.add("value4");

        titleArr.add("类型");
        titleArr.add("买入");
        titleArr.add("卖出");
        titleArr.add("分红");

        showtailArr.add("0");
        showtailArr.add("2");
        showtailArr.add("2");
        showtailArr.add("2");

        ispercentArr.add("0");
        ispercentArr.add("1");
        ispercentArr.add("1");
        ispercentArr.add("1");

        dataList.add(baseFormMap);
        dataList.add(baseFormMap1);
        dataList.add(baseFormMap2);
        dataList.add(baseFormMap3);
        dataList.add(baseFormMap4);
        dataList.add(baseFormMap5);


        SXSSFWorkbook wb = new SXSSFWorkbook();
        SXSSFSheet sheet = wb.createSheet("Sheet1");

        result=createChart(wb,sheet,10,"bar", STBarGrouping.STACKED,false,false,dataList, fldNameArr, titleArr,showtailArr,ispercentArr);

        result=createChart(wb,sheet,10+dataList.size()+12,"bar",STBarGrouping.CLUSTERED,true,true,dataList, fldNameArr, titleArr,showtailArr,ispercentArr);


        //System.out.println(ctChart);
        System.out.println(result);

        FileOutputStream fileOut = new FileOutputStream("D://BarChart.xlsx");
        wb.write(fileOut);
        fileOut.close();
    }

    /**
     * @Description: 创建Excel数据
     * @param wb:工作薄
     * @param sheet：wb.createSheet();
     * @param dataList
     * @param fldNameArr
     * @param titleArr
     * @param showtailArr
     * @param ispercentArr
     * @param position:从第几行开始(0：就是第一行)
     * @return boolean
     */
    public static boolean refreshChartExcel(SXSSFWorkbook wb, SXSSFSheet sheet,
                                            List<BaseFormMap> dataList, List<String> fldNameArr, List<String> titleArr,
                                            List<String> showtailArr, List<String> ispercentArr, int position) {
        boolean result = true;
        //样式准备
        CellStyle style = wb.createCellStyle();
        style.setFillForegroundColor(IndexedColors.ROYAL_BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderBottom(BorderStyle.THIN); //下边框
        style.setBorderLeft(BorderStyle.THIN);//左边框
        style.setBorderTop(BorderStyle.THIN);//上边框
        style.setBorderRight(BorderStyle.THIN);//右边框
        style.setAlignment(HorizontalAlignment.CENTER);

        CellStyle style1 = wb.createCellStyle();
        style1.setBorderBottom(BorderStyle.THIN); //下边框
        style1.setBorderLeft(BorderStyle.THIN);//左边框
        style1.setBorderTop(BorderStyle.THIN);//上边框
        style1.setBorderRight(BorderStyle.THIN);//右边框
        style1.setAlignment(HorizontalAlignment.CENTER);

        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setBorderTop(BorderStyle.THIN);//上边框
        cellStyle.setBorderBottom(BorderStyle.THIN); //下边框
        cellStyle.setBorderLeft(BorderStyle.THIN);//左边框
        cellStyle.setBorderRight(BorderStyle.THIN);//右边框
        cellStyle.setAlignment(HorizontalAlignment.CENTER);//水平对齐方式
        //cellStyle.setVerticalAlignment(VerticalAlignment.TOP);//垂直对齐方式

        //根据数据创建excel第一行标题行
        for (int i = 0; i < titleArr.size(); i++) {
            if(sheet.getRow(position)==null){
                sheet.createRow(position).createCell(i).setCellValue(titleArr.get(i)==null?"":titleArr.get(i));

            }else{
                sheet.getRow(position).createCell(i).setCellValue(titleArr.get(i)==null?"":titleArr.get(i));
            }
            //标题行创建背景颜色
            sheet.getRow(position).getCell(i).setCellStyle(style);
        }

        //遍历数据行
        for (int i = 0; i < dataList.size(); i++) {
            BaseFormMap baseFormMap = dataList.get(i);//数据行
            //fldNameArr字段属性
            for (int j = 0; j < fldNameArr.size(); j++) {
                if(sheet.getRow(position+i+1)==null){
                    if(j==0){
                        try {
                            sheet.createRow(position+i+1).createCell(j).setCellValue(((String) baseFormMap.get(fldNameArr.get(j)))==null?"":((String)baseFormMap.get(fldNameArr.get(j))));
                        } catch (Exception e) {
                            // TODO
                            if((String)baseFormMap.get(fldNameArr.get(j))==null){
                                sheet.createRow(position+i+1).createCell(j).setCellValue("");
                            }else{
                                sheet.createRow(position+i+1).createCell(j).setCellValue((Date)baseFormMap.get(fldNameArr.get(j)));
                            }
                        }
                    }
                    //标题行创建背景颜色
                    sheet.getRow(position+i+1).getCell(j).setCellStyle(style1);
                }else{
                    BigDecimal b=(BigDecimal)baseFormMap.get(fldNameArr.get(j));
                    double value=0d;
                    if(b!=null){
                        value=b.doubleValue();
                    }
                    if(value==0){
                        sheet.getRow(position+i+1).createCell(j);
                    }else{
                        sheet.getRow(position+i+1).createCell(j).setCellValue(((BigDecimal)baseFormMap.get(fldNameArr.get(j))).doubleValue());
                    }
                    if("1".equals(ispercentArr.get(j))){//是否设置百分比
                        // 设置Y轴的数字为百分比样式显示
                        StringBuilder sb=new StringBuilder();

                        if("0".equals(showtailArr.get(j))){//保留几位小数
                            sb.append("0");
                            if("1".equals(ispercentArr.get(j))){//是否百分比
                                sb.append("%");
                            }
                        }else{
                            sb.append("0.");
                            for(int k=0;k<Integer.parseInt(showtailArr.get(j));k++){
                                sb.append("0");
                            }
                            if("1".equals(ispercentArr.get(j))){//是否百分比
                                sb.append("%");
                            }
                        }
                        cellStyle.setDataFormat(wb.createDataFormat().getFormat(sb.toString()));
                        sheet.getRow(position+i+1).getCell(j).setCellStyle(cellStyle);
                    }else{
                        //是否设置百分比
                        // 设置Y轴的数字为百分比样式显示
                        StringBuilder sb=new StringBuilder();

                        if("0".equals(showtailArr.get(j))){//保留几位小数
                            sb.append("0");
                        }else{
                            sb.append("0.");
                            for(int k=0;k<Integer.parseInt(showtailArr.get(j));k++){
                                sb.append("0");
                            }
                        }
                        cellStyle.setDataFormat(wb.createDataFormat().getFormat(sb.toString()));
                        sheet.getRow(position+i+1).getCell(j).setCellStyle(cellStyle);
                    }
                }
            }

        }
        return result;
    }

    /**
     * @Description:创建动态图
     * @param wb
     * @param sheet
     * @param curRow:当前行号
     * @param type:图类型
     * @param group:柱状图类型 @see STBarGrouping
     * @param isLegend:是否添加图注
     * @param isvalAxis:是否添加Y左轴
     * @param dataList:数据
     * @param fldNameArr:属性
     * @param titleArr:标题
     * @param showtailArr:保留几位小数
     * @param ispercentArr:是否百分比
     * @return
     */
    public static boolean createChart(SXSSFWorkbook wb, SXSSFSheet sheet, int curRow, String type, STBarGrouping.Enum group, boolean isLegend, boolean isvalAxis, List<BaseFormMap> dataList, List<String> fldNameArr, List<String> titleArr, List<String> showtailArr, List<String> ispercentArr) throws Exception{
        boolean result=false;
        String sheetName=sheet.getSheetName();
        //动态表sheet刷新
        result=refreshChartExcel(wb, sheet,dataList, fldNameArr, titleArr, showtailArr, ispercentArr, curRow);

        //创建一个画布
        Drawing drawing = sheet.createDrawingPatriarch();

        //前四个默认0，[0,5]：从0列5行开始;[6,20]:宽度6，20向下扩展到20行
        //默认宽度(14-8)*12
        ClientAnchor anchor =null;
        if(dataList.size()<10){
            anchor=drawing.createAnchor(0, 0, 0, 0, 0, curRow+dataList.size()+1, 6, curRow+dataList.size()+12);
        }else{
            anchor=drawing.createAnchor(0, 0, 0, 0, 0, curRow+dataList.size()+1, (int)Math.round(dataList.size()*0.5), curRow+dataList.size()+12);
        }


        //创建一个chart对象
        Chart chart = drawing.createChart(anchor);
        CTChart ctChart = ((XSSFChart)chart).getCTChart();
        CTPlotArea ctPlotArea = ctChart.getPlotArea();
        if("bar".equals(type)){
            CTBarChart ctBarChart = ctPlotArea.addNewBarChart();
            CTBoolean ctBoolean=ctBarChart.addNewVaryColors();
            ctBarChart.getVaryColors().setVal(true);

            //设置类型
            ctBarChart.addNewGrouping().setVal(group);
            ctBoolean.setVal(true);
            ctBarChart.addNewBarDir().setVal(STBarDir.COL);

            //是否添加左侧坐标轴
            ctChart.addNewDispBlanksAs().setVal(STDispBlanksAs.ZERO);
            ctChart.addNewShowDLblsOverMax().setVal(true);

            //设置这两个参数是为了在STACKED模式下生成堆积模式；(standard)标准模式时需要将这两行去掉
            if("stacked".equals(group.toString())||"percentStacked".equals(group.toString())){
                ctBarChart.addNewGapWidth().setVal(150);
                ctBarChart.addNewOverlap().setVal((byte)100);
            }

            //创建序列,并且设置选中区域
            for (int i = 0; i < fldNameArr.size()-1; i++) {
                CTBarSer ctBarSer = ctBarChart.addNewSer();
                CTSerTx ctSerTx = ctBarSer.addNewTx();
                //图例区
                CTStrRef ctStrRef = ctSerTx.addNewStrRef();
                String legendDataRange = new CellRangeAddress(curRow,curRow, i+1, i+1).formatAsString(sheetName, true);
                ctStrRef.setF(legendDataRange);
                ctBarSer.addNewIdx().setVal(i);

                //横坐标区
                CTAxDataSource cttAxDataSource = ctBarSer.addNewCat();
                ctStrRef = cttAxDataSource.addNewStrRef();
                String axisDataRange = new CellRangeAddress(curRow+1, curRow+dataList.size(), 0, 0)
                        .formatAsString(sheetName, true);
                ctStrRef.setF(axisDataRange);

                //数据区域
                CTNumDataSource ctNumDataSource = ctBarSer.addNewVal();
                CTNumRef ctNumRef = ctNumDataSource.addNewNumRef();
                String numDataRange = new CellRangeAddress(curRow+1, curRow+dataList.size(), i+1, i+1)
                        .formatAsString(sheetName, true);
                ctNumRef.setF(numDataRange);

                ctBarSer.addNewSpPr().addNewLn().addNewSolidFill().addNewSrgbClr().setVal(new byte[] {0,0,0});

                //设置负轴颜色不是白色
                ctBarSer.addNewInvertIfNegative().setVal(false);
                //设置标签格式
                ctBoolean.setVal(false);
                CTDLbls newDLbls = ctBarSer.addNewDLbls();
                newDLbls.setShowLegendKey(ctBoolean);
                ctBoolean.setVal(true);
                newDLbls.setShowVal(ctBoolean);
                ctBoolean.setVal(false);
                newDLbls.setShowCatName(ctBoolean);
                newDLbls.setShowSerName(ctBoolean);
                newDLbls.setShowPercent(ctBoolean);
                newDLbls.setShowBubbleSize(ctBoolean);
                newDLbls.setShowLeaderLines(ctBoolean);
            }

            //telling the BarChart that it has axes and giving them Ids
            ctBarChart.addNewAxId().setVal(123456);
            ctBarChart.addNewAxId().setVal(123457);

            //cat axis
            CTCatAx ctCatAx = ctPlotArea.addNewCatAx();
            ctCatAx.addNewAxId().setVal(123456); //id of the cat axis
            CTScaling ctScaling = ctCatAx.addNewScaling();
            ctScaling.addNewOrientation().setVal(STOrientation.MIN_MAX);
            ctCatAx.addNewAxPos().setVal(STAxPos.B);
            ctCatAx.addNewCrossAx().setVal(123457); //id of the val axis
            ctCatAx.addNewTickLblPos().setVal(STTickLblPos.NEXT_TO);

            //val axis
            CTValAx ctValAx = ctPlotArea.addNewValAx();
            ctValAx.addNewAxId().setVal(123457); //id of the val axis
            ctScaling = ctValAx.addNewScaling();
            ctScaling.addNewOrientation().setVal(STOrientation.MIN_MAX);
            //设置位置
            ctValAx.addNewAxPos().setVal(STAxPos.L);
            ctValAx.addNewCrossAx().setVal(123456); //id of the cat axis
            ctValAx.addNewTickLblPos().setVal(STTickLblPos.NEXT_TO);

            //是否删除主左边轴
            if(isvalAxis){
                ctValAx.addNewDelete().setVal(false);
            }else{
                ctValAx.addNewDelete().setVal(true);
            }

            //是否删除横坐标
            ctCatAx.addNewDelete().setVal(false);

            //legend图注
            if(isLegend){
                CTLegend ctLegend = ctChart.addNewLegend();
                ctLegend.addNewLegendPos().setVal(STLegendPos.B);
                ctLegend.addNewOverlay().setVal(false);
            }
        }else if("line".equals(type)){
            CTLineChart ctLineChart = ctPlotArea.addNewLineChart();
            CTBoolean ctBoolean = ctLineChart.addNewVaryColors();
            ctLineChart.addNewGrouping().setVal(STGrouping.STANDARD);

            //创建序列,并且设置选中区域
            for (int i = 0; i < fldNameArr.size()-1; i++) {
                CTLineSer ctLineSer = ctLineChart.addNewSer();
                CTSerTx ctSerTx = ctLineSer.addNewTx();
                //图例区
                CTStrRef ctStrRef = ctSerTx.addNewStrRef();
                String legendDataRange = new CellRangeAddress(curRow,curRow, i+1, i+1).formatAsString(sheetName, true);
                ctStrRef.setF(legendDataRange);
                ctStrRef.setF(legendDataRange);
                ctLineSer.addNewIdx().setVal(i);

                //横坐标区
                CTAxDataSource cttAxDataSource = ctLineSer.addNewCat();
                ctStrRef = cttAxDataSource.addNewStrRef();
                String axisDataRange = new CellRangeAddress(curRow+1, curRow+dataList.size(), 0, 0)
                        .formatAsString(sheetName, true);
                ctStrRef.setF(axisDataRange);

                //数据区域
                CTNumDataSource ctNumDataSource = ctLineSer.addNewVal();
                CTNumRef ctNumRef = ctNumDataSource.addNewNumRef();
                String numDataRange = new CellRangeAddress(curRow+1, curRow+dataList.size(), i+1, i+1)
                        .formatAsString(sheetName, true);
                ctNumRef.setF(numDataRange);

                //设置标签格式
                ctBoolean.setVal(false);
                CTDLbls newDLbls = ctLineSer.addNewDLbls();
                newDLbls.setShowLegendKey(ctBoolean);
                ctBoolean.setVal(true);
                newDLbls.setShowVal(ctBoolean);
                ctBoolean.setVal(false);
                newDLbls.setShowCatName(ctBoolean);
                newDLbls.setShowSerName(ctBoolean);
                newDLbls.setShowPercent(ctBoolean);
                newDLbls.setShowBubbleSize(ctBoolean);
                newDLbls.setShowLeaderLines(ctBoolean);

                //是否是平滑曲线
                CTBoolean addNewSmooth = ctLineSer.addNewSmooth();
                addNewSmooth.setVal(false);

                //是否是堆积曲线
                CTMarker addNewMarker = ctLineSer.addNewMarker();
                CTMarkerStyle addNewSymbol = addNewMarker.addNewSymbol();
                addNewSymbol.setVal(STMarkerStyle.NONE);
            }
            //telling the BarChart that it has axes and giving them Ids
            ctLineChart.addNewAxId().setVal(123456);
            ctLineChart.addNewAxId().setVal(123457);

            //cat axis
            CTCatAx ctCatAx = ctPlotArea.addNewCatAx();
            ctCatAx.addNewAxId().setVal(123456); //id of the cat axis
            CTScaling ctScaling = ctCatAx.addNewScaling();
            ctScaling.addNewOrientation().setVal(STOrientation.MIN_MAX);
            ctCatAx.addNewAxPos().setVal(STAxPos.B);
            ctCatAx.addNewCrossAx().setVal(123457); //id of the val axis
            ctCatAx.addNewTickLblPos().setVal(STTickLblPos.NEXT_TO);

            //val axis
            CTValAx ctValAx = ctPlotArea.addNewValAx();
            ctValAx.addNewAxId().setVal(123457); //id of the val axis
            ctScaling = ctValAx.addNewScaling();
            ctScaling.addNewOrientation().setVal(STOrientation.MIN_MAX);
            ctValAx.addNewAxPos().setVal(STAxPos.L);
            ctValAx.addNewCrossAx().setVal(123456); //id of the cat axis
            ctValAx.addNewTickLblPos().setVal(STTickLblPos.NEXT_TO);

            //是否删除主左边轴
            if(isvalAxis){
                ctValAx.addNewDelete().setVal(false);
            }else{
                ctValAx.addNewDelete().setVal(true);
            }

            //是否删除横坐标
            ctCatAx.addNewDelete().setVal(false);

            //legend图注
            if(isLegend){
                CTLegend ctLegend = ctChart.addNewLegend();
                ctLegend.addNewLegendPos().setVal(STLegendPos.B);
                ctLegend.addNewOverlay().setVal(false);
            }
        }else if("bar-line-2".equals(type)){
            CTBarChart ctBarChart = ctPlotArea.addNewBarChart();
            CTBoolean ctBoolean=ctBarChart.addNewVaryColors();
            ctBarChart.getVaryColors().setVal(true);

            //设置类型
            ctBarChart.addNewGrouping().setVal(STBarGrouping.CLUSTERED);
            ctBoolean.setVal(true);
            ctBarChart.addNewBarDir().setVal(STBarDir.COL);

            //是否添加左侧坐标轴
            ctChart.addNewDispBlanksAs().setVal(STDispBlanksAs.ZERO);
            ctChart.addNewShowDLblsOverMax().setVal(true);

            //创建序列,并且设置选中区域
            for (int i = 0; i < fldNameArr.size()-2; i++) {
                CTBarSer ctBarSer = ctBarChart.addNewSer();
                CTSerTx ctSerTx = ctBarSer.addNewTx();
                //图例区
                CTStrRef ctStrRef = ctSerTx.addNewStrRef();
                String legendDataRange = new CellRangeAddress(curRow,curRow, i+1, i+1).formatAsString(sheetName, true);
                ctStrRef.setF(legendDataRange);
                ctBarSer.addNewIdx().setVal(i);

                //横坐标区
                CTAxDataSource cttAxDataSource = ctBarSer.addNewCat();
                ctStrRef = cttAxDataSource.addNewStrRef();
                String axisDataRange = new CellRangeAddress(curRow+1, curRow+dataList.size(), 0, 0)
                        .formatAsString(sheetName, true);
                ctStrRef.setF(axisDataRange);

                //数据区域
                CTNumDataSource ctNumDataSource = ctBarSer.addNewVal();
                CTNumRef ctNumRef = ctNumDataSource.addNewNumRef();
                String numDataRange = new CellRangeAddress(curRow+1, curRow+dataList.size(), i+1, i+1)
                        .formatAsString(sheetName, true);
                ctNumRef.setF(numDataRange);

                ctBarSer.addNewSpPr().addNewLn().addNewSolidFill().addNewSrgbClr().setVal(new byte[] {0,0,0});
                //设置负轴颜色不是白色
                ctBarSer.addNewInvertIfNegative().setVal(false);
                //设置标签格式
                ctBoolean.setVal(false);
                CTDLbls newDLbls = ctBarSer.addNewDLbls();
                newDLbls.setShowLegendKey(ctBoolean);
                ctBoolean.setVal(true);
                newDLbls.setShowVal(ctBoolean);
                ctBoolean.setVal(false);
                newDLbls.setShowCatName(ctBoolean);
                newDLbls.setShowSerName(ctBoolean);
                newDLbls.setShowPercent(ctBoolean);
                newDLbls.setShowBubbleSize(ctBoolean);
                newDLbls.setShowLeaderLines(ctBoolean);
            }

            //telling the BarChart that it has axes and giving them Ids
            ctBarChart.addNewAxId().setVal(123456);
            ctBarChart.addNewAxId().setVal(123457);

            //telling the BarChart that it has axes and giving them Ids
            ctBarChart.addNewAxId().setVal(123456);
            ctBarChart.addNewAxId().setVal(123457);

            //cat axis
            CTCatAx ctCatAx = ctPlotArea.addNewCatAx();
            ctCatAx.addNewAxId().setVal(123456); //id of the cat axis
            CTScaling ctScaling = ctCatAx.addNewScaling();
            ctScaling.addNewOrientation().setVal(STOrientation.MIN_MAX);
            ctCatAx.addNewAxPos().setVal(STAxPos.B);
            ctCatAx.addNewCrossAx().setVal(123457); //id of the val axis
            ctCatAx.addNewTickLblPos().setVal(STTickLblPos.NEXT_TO);

            //val axis
            CTValAx ctValAx = ctPlotArea.addNewValAx();
            ctValAx.addNewAxId().setVal(123457); //id of the val axis
            ctScaling = ctValAx.addNewScaling();
            ctScaling.addNewOrientation().setVal(STOrientation.MIN_MAX);
            ctValAx.addNewAxPos().setVal(STAxPos.L);
            ctValAx.addNewCrossAx().setVal(123456); //id of the cat axis
            ctValAx.addNewTickLblPos().setVal(STTickLblPos.NEXT_TO);

            //是否删除主左边轴
            if(isvalAxis){
                ctValAx.addNewDelete().setVal(false);
            }else{
                ctValAx.addNewDelete().setVal(true);
            }

            //是否删除横坐标
            ctCatAx.addNewDelete().setVal(false);

            //legend图注
            if(isLegend){
                CTLegend ctLegend = ctChart.addNewLegend();
                ctLegend.addNewLegendPos().setVal(STLegendPos.B);
                ctLegend.addNewOverlay().setVal(false);
            }


            CTLineChart ctLineChart = ctPlotArea.addNewLineChart();
            ctLineChart.addNewGrouping().setVal(STGrouping.STANDARD);

            //创建序列,并且设置选中区域
            for (int i =1; i < fldNameArr.size()-1; i++) {
                CTLineSer ctLineSer = ctLineChart.addNewSer();
                CTSerTx ctSerTx = ctLineSer.addNewTx();
                //图例区
                CTStrRef ctStrRef = ctSerTx.addNewStrRef();
                String legendDataRange = new CellRangeAddress(curRow,curRow, i+1, i+1).formatAsString(sheetName, true);
                ctStrRef.setF(legendDataRange);
                ctStrRef.setF(legendDataRange);
                ctLineSer.addNewIdx().setVal(i);

                //横坐标区
                CTAxDataSource cttAxDataSource = ctLineSer.addNewCat();
                ctStrRef = cttAxDataSource.addNewStrRef();
                String axisDataRange = new CellRangeAddress(curRow+1, curRow+dataList.size(), 0, 0)
                        .formatAsString(sheetName, true);
                ctStrRef.setF(axisDataRange);

                //数据区域
                CTNumDataSource ctNumDataSource = ctLineSer.addNewVal();
                CTNumRef ctNumRef = ctNumDataSource.addNewNumRef();
                String numDataRange = new CellRangeAddress(curRow+1, curRow+dataList.size(), i+1, i+1)
                        .formatAsString(sheetName, true);
                ctNumRef.setF(numDataRange);

                //是否是平滑曲线
                CTBoolean addNewSmooth = ctLineSer.addNewSmooth();
                addNewSmooth.setVal(false);

                //是否是堆积曲线
                CTMarker addNewMarker = ctLineSer.addNewMarker();
                CTMarkerStyle addNewSymbol = addNewMarker.addNewSymbol();
                addNewSymbol.setVal(STMarkerStyle.NONE);
            }

            //telling the BarChart that it has axes and giving them Ids
            //TODO:写死是否有影响？
            ctLineChart.addNewAxId().setVal(1234567);
            ctLineChart.addNewAxId().setVal(1234578);

            //cat axis
            CTCatAx ctCatAxline = ctPlotArea.addNewCatAx();
            ctCatAxline.addNewAxId().setVal(1234567); //id of the cat axis
            CTScaling ctScalingline = ctCatAxline.addNewScaling();
            ctScalingline.addNewOrientation().setVal(STOrientation.MIN_MAX);
            ctCatAxline.addNewDelete().setVal(true);
            ctCatAxline.addNewAxPos().setVal(STAxPos.B);
            ctCatAxline.addNewMajorTickMark().setVal(STTickMark.OUT);
            ctCatAxline.addNewMinorTickMark().setVal(STTickMark.NONE);
            ctCatAxline.addNewAuto().setVal(true);
            ctCatAxline.addNewLblAlgn().setVal(STLblAlgn.CTR);
            ctCatAxline.addNewLblOffset().setVal(100);
            ctCatAxline.addNewNoMultiLvlLbl().setVal(false);
            ctCatAxline.addNewCrossAx().setVal(1234578); //id of the val axis
            ctCatAxline.addNewTickLblPos().setVal(STTickLblPos.NEXT_TO);

            //val axis
            CTValAx ctValAxline = ctPlotArea.addNewValAx();
            ctValAxline.addNewAxId().setVal(1234578); //id of the val axis
            ctScalingline = ctValAxline.addNewScaling();
            ctScalingline.addNewOrientation().setVal(STOrientation.MIN_MAX);
            //Y轴右侧坐标true删除，false保留
            ctValAxline.addNewDelete().setVal(false);
            ctValAxline.addNewAxPos().setVal(STAxPos.R);
            ctValAxline.addNewMajorTickMark().setVal(STTickMark.OUT);
            ctValAxline.addNewMinorTickMark().setVal(STTickMark.NONE);
            ctValAxline.addNewCrosses().setVal(STCrosses.MAX);
            ctValAxline.addNewCrossBetween().setVal(STCrossBetween.BETWEEN);
            ctValAxline.addNewCrossAx().setVal(1234567); //id of the cat axis
            ctValAxline.addNewTickLblPos().setVal(STTickLblPos.NEXT_TO);
        }else if("bar-line-4".equals(type)){
            CTBarChart ctBarChart = ctPlotArea.addNewBarChart();
            CTBoolean ctBoolean=ctBarChart.addNewVaryColors();
            ctBarChart.getVaryColors().setVal(true);

            //设置类型
            ctBarChart.addNewGrouping().setVal(STBarGrouping.CLUSTERED);
            ctBoolean.setVal(true);
            ctBarChart.addNewBarDir().setVal(STBarDir.COL);

            //是否添加左侧坐标轴
            ctChart.addNewDispBlanksAs().setVal(STDispBlanksAs.ZERO);
            ctChart.addNewShowDLblsOverMax().setVal(true);

            //创建序列,并且设置选中区域
            for (int i = 0; i < fldNameArr.size()-1-2; i++) {
                CTBarSer ctBarSer = ctBarChart.addNewSer();
                CTSerTx ctSerTx = ctBarSer.addNewTx();
                //图例区
                CTStrRef ctStrRef = ctSerTx.addNewStrRef();
                String legendDataRange = new CellRangeAddress(curRow,curRow, i+1, i+1).formatAsString(sheetName, true);
                ctStrRef.setF(legendDataRange);
                ctBarSer.addNewIdx().setVal(i);

                //横坐标区
                CTAxDataSource cttAxDataSource = ctBarSer.addNewCat();
                ctStrRef = cttAxDataSource.addNewStrRef();
                String axisDataRange = new CellRangeAddress(curRow+1, curRow+dataList.size(), 0, 0)
                        .formatAsString(sheetName, true);
                ctStrRef.setF(axisDataRange);

                //数据区域
                CTNumDataSource ctNumDataSource = ctBarSer.addNewVal();
                CTNumRef ctNumRef = ctNumDataSource.addNewNumRef();
                String numDataRange = new CellRangeAddress(curRow+1, curRow+dataList.size(), i+1, i+1)
                        .formatAsString(sheetName, true);
                ctNumRef.setF(numDataRange);

                ctBarSer.addNewSpPr().addNewLn().addNewSolidFill().addNewSrgbClr().setVal(new byte[] {0,0,0});
                //设置负轴颜色不是白色
                ctBarSer.addNewInvertIfNegative().setVal(false);
                //设置标签格式
                ctBoolean.setVal(false);
                CTDLbls newDLbls = ctBarSer.addNewDLbls();
                newDLbls.setShowLegendKey(ctBoolean);
                ctBoolean.setVal(true);
                newDLbls.setShowVal(ctBoolean);
                ctBoolean.setVal(false);
                newDLbls.setShowCatName(ctBoolean);
                newDLbls.setShowSerName(ctBoolean);
                newDLbls.setShowPercent(ctBoolean);
                newDLbls.setShowBubbleSize(ctBoolean);
                newDLbls.setShowLeaderLines(ctBoolean);
            }

            //telling the BarChart that it has axes and giving them Ids
            ctBarChart.addNewAxId().setVal(123456);
            ctBarChart.addNewAxId().setVal(123457);

            //telling the BarChart that it has axes and giving them Ids
            ctBarChart.addNewAxId().setVal(123456);
            ctBarChart.addNewAxId().setVal(123457);

            //cat axis
            CTCatAx ctCatAx = ctPlotArea.addNewCatAx();
            ctCatAx.addNewAxId().setVal(123456); //id of the cat axis
            CTScaling ctScaling = ctCatAx.addNewScaling();
            ctScaling.addNewOrientation().setVal(STOrientation.MIN_MAX);
            ctCatAx.addNewAxPos().setVal(STAxPos.B);
            ctCatAx.addNewCrossAx().setVal(123457); //id of the val axis
            ctCatAx.addNewTickLblPos().setVal(STTickLblPos.NEXT_TO);

            //val axis
            CTValAx ctValAx = ctPlotArea.addNewValAx();
            ctValAx.addNewAxId().setVal(123457); //id of the val axis
            ctScaling = ctValAx.addNewScaling();
            ctScaling.addNewOrientation().setVal(STOrientation.MIN_MAX);
            ctValAx.addNewAxPos().setVal(STAxPos.L);
            ctValAx.addNewCrossAx().setVal(123456); //id of the cat axis
            ctValAx.addNewTickLblPos().setVal(STTickLblPos.NEXT_TO);

            //是否删除主左边轴
            if(isvalAxis){
                ctValAx.addNewDelete().setVal(false);
            }else{
                ctValAx.addNewDelete().setVal(true);
            }

            //是否删除横坐标
            ctCatAx.addNewDelete().setVal(false);

            //legend图注
            if(isLegend){
                CTLegend ctLegend = ctChart.addNewLegend();
                ctLegend.addNewLegendPos().setVal(STLegendPos.B);
                ctLegend.addNewOverlay().setVal(false);
            }


            CTLineChart ctLineChart = ctPlotArea.addNewLineChart();
            ctLineChart.addNewGrouping().setVal(STGrouping.STANDARD);

            //创建序列,并且设置选中区域
            for (int i = 2; i < fldNameArr.size()-1; i++) {
                CTLineSer ctLineSer = ctLineChart.addNewSer();
                CTSerTx ctSerTx = ctLineSer.addNewTx();
                //图例区
                CTStrRef ctStrRef = ctSerTx.addNewStrRef();
                String legendDataRange = new CellRangeAddress(curRow,curRow, i+1, i+1).formatAsString(sheetName, true);
                ctStrRef.setF(legendDataRange);
                ctStrRef.setF(legendDataRange);
                ctLineSer.addNewIdx().setVal(i);

                //横坐标区
                CTAxDataSource cttAxDataSource = ctLineSer.addNewCat();
                ctStrRef = cttAxDataSource.addNewStrRef();
                String axisDataRange = new CellRangeAddress(curRow+1, curRow+dataList.size(), 0, 0)
                        .formatAsString(sheetName, true);
                ctStrRef.setF(axisDataRange);

                //数据区域
                CTNumDataSource ctNumDataSource = ctLineSer.addNewVal();
                CTNumRef ctNumRef = ctNumDataSource.addNewNumRef();
                String numDataRange = new CellRangeAddress(curRow+1, curRow+dataList.size(), i+1, i+1)
                        .formatAsString(sheetName, true);
                ctNumRef.setF(numDataRange);

                //是否是平滑曲线
                CTBoolean addNewSmooth = ctLineSer.addNewSmooth();
                addNewSmooth.setVal(false);

                //是否是堆积曲线
                CTMarker addNewMarker = ctLineSer.addNewMarker();
                CTMarkerStyle addNewSymbol = addNewMarker.addNewSymbol();
                addNewSymbol.setVal(STMarkerStyle.NONE);
            }

            //telling the BarChart that it has axes and giving them Ids
            //TODO:写死是否有影响？
            ctLineChart.addNewAxId().setVal(1234567);
            ctLineChart.addNewAxId().setVal(1234578);

            //cat axis
            CTCatAx ctCatAxline = ctPlotArea.addNewCatAx();
            ctCatAxline.addNewAxId().setVal(1234567); //id of the cat axis
            CTScaling ctScalingline = ctCatAxline.addNewScaling();
            ctScalingline.addNewOrientation().setVal(STOrientation.MIN_MAX);
            ctCatAxline.addNewDelete().setVal(true);
            ctCatAxline.addNewAxPos().setVal(STAxPos.B);
            ctCatAxline.addNewMajorTickMark().setVal(STTickMark.OUT);
            ctCatAxline.addNewMinorTickMark().setVal(STTickMark.NONE);
            ctCatAxline.addNewAuto().setVal(true);
            ctCatAxline.addNewLblAlgn().setVal(STLblAlgn.CTR);
            ctCatAxline.addNewLblOffset().setVal(100);
            ctCatAxline.addNewNoMultiLvlLbl().setVal(false);
            ctCatAxline.addNewCrossAx().setVal(1234578); //id of the val axis
            ctCatAxline.addNewTickLblPos().setVal(STTickLblPos.NEXT_TO);

            //val axis
            CTValAx ctValAxline = ctPlotArea.addNewValAx();
            ctValAxline.addNewAxId().setVal(1234578); //id of the val axis
            ctScalingline = ctValAxline.addNewScaling();
            ctScalingline.addNewOrientation().setVal(STOrientation.MIN_MAX);
            ctValAxline.addNewDelete().setVal(false);
            ctValAxline.addNewAxPos().setVal(STAxPos.R);
            ctValAxline.addNewMajorTickMark().setVal(STTickMark.OUT);
            ctValAxline.addNewMinorTickMark().setVal(STTickMark.NONE);
            ctValAxline.addNewCrosses().setVal(STCrosses.MAX);
            ctValAxline.addNewCrossBetween().setVal(STCrossBetween.BETWEEN);
            ctValAxline.addNewCrossAx().setVal(1234567); //id of the cat axis
            ctValAxline.addNewTickLblPos().setVal(STTickLblPos.NEXT_TO);
        }else if("pie".equals(type)){//pie
            CTPieChart ctPieChart = ctPlotArea.addNewPieChart();
            CTBoolean ctBoolean = ctPieChart.addNewVaryColors();

            //创建序列,并且设置选中区域
            for (int i = 0; i < fldNameArr.size()-1; i++) {
                CTPieSer ctPieSer = ctPieChart.addNewSer();
                CTSerTx ctSerTx = ctPieSer.addNewTx();
                //图例区
                CTStrRef ctStrRef = ctSerTx.addNewStrRef();
                String legendDataRange = new CellRangeAddress(curRow,curRow, i+1, i+1).formatAsString(sheetName, true);
                ctStrRef.setF(legendDataRange);
                ctPieSer.addNewIdx().setVal(i);

                //横坐标区
                CTAxDataSource cttAxDataSource = ctPieSer.addNewCat();
                ctStrRef = cttAxDataSource.addNewStrRef();
                String axisDataRange = new CellRangeAddress(curRow+1, curRow+dataList.size(), 0, 0)
                        .formatAsString(sheetName, true);
                ctStrRef.setF(axisDataRange);

                //数据区域
                CTNumDataSource ctNumDataSource = ctPieSer.addNewVal();
                CTNumRef ctNumRef = ctNumDataSource.addNewNumRef();
                String numDataRange = new CellRangeAddress(curRow+1, curRow+dataList.size(), i+1, i+1)
                        .formatAsString(sheetName, true);
                ctNumRef.setF(numDataRange);

                ctPieSer.addNewSpPr().addNewLn().addNewSolidFill().addNewSrgbClr().setVal(new byte[] {0,0,0});

                //设置标签格式
                ctBoolean.setVal(true);
            }
            //legend图注
            CTLegend ctLegend = ctChart.addNewLegend();
            ctLegend.addNewLegendPos().setVal(STLegendPos.B);
            ctLegend.addNewOverlay().setVal(true);
        }else if("pie3D".equals(type)){//pie3D
            CTPie3DChart ctPie3DChart = ctPlotArea.addNewPie3DChart();
            CTBoolean ctBoolean = ctPie3DChart.addNewVaryColors();

            //创建序列,并且设置选中区域
            for (int i = 0; i < fldNameArr.size()-1; i++) {
                CTPieSer ctPieSer = ctPie3DChart.addNewSer();
                CTSerTx ctSerTx = ctPieSer.addNewTx();
                //图例区
                CTStrRef ctStrRef = ctSerTx.addNewStrRef();
                String legendDataRange = new CellRangeAddress(curRow,curRow, i+1, i+1).formatAsString(sheetName, true);
                ctStrRef.setF(legendDataRange);
                ctPieSer.addNewIdx().setVal(i);

                //横坐标区
                CTAxDataSource cttAxDataSource = ctPieSer.addNewCat();
                ctStrRef = cttAxDataSource.addNewStrRef();
                String axisDataRange = new CellRangeAddress(curRow+1, curRow+dataList.size(), 0, 0)
                        .formatAsString(sheetName, true);
                ctStrRef.setF(axisDataRange);

                //数据区域
                CTNumDataSource ctNumDataSource = ctPieSer.addNewVal();
                CTNumRef ctNumRef = ctNumDataSource.addNewNumRef();
                String numDataRange = new CellRangeAddress(curRow+1, curRow+dataList.size(), i+1, i+1)
                        .formatAsString(sheetName, true);
                ctNumRef.setF(numDataRange);

                ctPieSer.addNewSpPr().addNewLn().addNewSolidFill().addNewSrgbClr().setVal(new byte[] {0,0,0});

                //设置标签格式
                ctBoolean.setVal(true);
            }
            //legend图注
            CTLegend ctLegend = ctChart.addNewLegend();
            ctLegend.addNewLegendPos().setVal(STLegendPos.B);
            ctLegend.addNewOverlay().setVal(true);
        }else{//area
            CTAreaChart ctAreaChart = ctPlotArea.addNewAreaChart();
            CTBoolean ctBoolean = ctAreaChart.addNewVaryColors();
            ctAreaChart.addNewGrouping().setVal(STGrouping.STANDARD);

            //创建序列,并且设置选中区域
            for (int i = 0; i < fldNameArr.size()-1; i++) {
                CTAreaSer ctAreaSer = ctAreaChart.addNewSer();
                CTSerTx ctSerTx = ctAreaSer.addNewTx();
                //图例区
                CTStrRef ctStrRef = ctSerTx.addNewStrRef();
                String legendDataRange = new CellRangeAddress(curRow,curRow, i+1, i+1).formatAsString(sheetName, true);
                ctStrRef.setF(legendDataRange);
                ctStrRef.setF(legendDataRange);
                ctAreaSer.addNewIdx().setVal(i);

                //横坐标区
                CTAxDataSource cttAxDataSource = ctAreaSer.addNewCat();
                ctStrRef = cttAxDataSource.addNewStrRef();
                String axisDataRange = new CellRangeAddress(curRow+1, curRow+dataList.size(), 0, 0)
                        .formatAsString(sheetName, true);
                ctStrRef.setF(axisDataRange);

                //数据区域
                CTNumDataSource ctNumDataSource = ctAreaSer.addNewVal();
                CTNumRef ctNumRef = ctNumDataSource.addNewNumRef();
                String numDataRange = new CellRangeAddress(curRow+1, curRow+dataList.size(), i+1, i+1)
                        .formatAsString(sheetName, true);
                ctNumRef.setF(numDataRange);

                //设置标签格式
                ctBoolean.setVal(false);
                CTDLbls newDLbls = ctAreaSer.addNewDLbls();
                newDLbls.setShowLegendKey(ctBoolean);
                ctBoolean.setVal(true);
                newDLbls.setShowVal(ctBoolean);
                ctBoolean.setVal(false);
                newDLbls.setShowCatName(ctBoolean);
                newDLbls.setShowSerName(ctBoolean);
                newDLbls.setShowPercent(ctBoolean);
                newDLbls.setShowBubbleSize(ctBoolean);
                newDLbls.setShowLeaderLines(ctBoolean);

     /* //是否是平滑曲线
           CTBoolean addNewSmooth = ctAreaSer.addNewSmooth();
           addNewSmooth.setVal(false);
           //是否是堆积曲线
           CTMarker addNewMarker = ctAreaSer.addNewMarker();
           CTMarkerStyle addNewSymbol = addNewMarker.addNewSymbol();
           addNewSymbol.setVal(STMarkerStyle.NONE);
           */
            }
            //telling the BarChart that it has axes and giving them Ids
            ctAreaChart.addNewAxId().setVal(123456);
            ctAreaChart.addNewAxId().setVal(123457);

            //cat axis
            CTCatAx ctCatAx = ctPlotArea.addNewCatAx();
            ctCatAx.addNewAxId().setVal(123456); //id of the cat axis
            CTScaling ctScaling = ctCatAx.addNewScaling();
            ctScaling.addNewOrientation().setVal(STOrientation.MIN_MAX);
            ctCatAx.addNewAxPos().setVal(STAxPos.B);
            ctCatAx.addNewCrossAx().setVal(123457); //id of the val axis
            ctCatAx.addNewTickLblPos().setVal(STTickLblPos.NEXT_TO);

            //val axis
            CTValAx ctValAx = ctPlotArea.addNewValAx();
            ctValAx.addNewAxId().setVal(123457); //id of the val axis
            ctScaling = ctValAx.addNewScaling();
            ctScaling.addNewOrientation().setVal(STOrientation.MIN_MAX);
            ctValAx.addNewAxPos().setVal(STAxPos.L);
            ctValAx.addNewCrossAx().setVal(123456); //id of the cat axis
            ctValAx.addNewTickLblPos().setVal(STTickLblPos.NEXT_TO);

            //是否删除主左边轴
            if(isvalAxis){
                ctValAx.addNewDelete().setVal(false);
            }else{
                ctValAx.addNewDelete().setVal(true);
            }

            //是否删除横坐标
            ctCatAx.addNewDelete().setVal(false);

            //legend图注
            if(isLegend){
                CTLegend ctLegend = ctChart.addNewLegend();
                ctLegend.addNewLegendPos().setVal(STLegendPos.B);
                ctLegend.addNewOverlay().setVal(false);
            }

        }

        return result;
    }
}
