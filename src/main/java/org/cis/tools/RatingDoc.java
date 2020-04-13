package org.cis.ui.doc;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.cis.backend.data.Rating;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

public class RatingDoc  {
    private String pib;
    private Collection<Rating> ratings;
    private ByteArrayOutputStream bos;

    public InputStream getBos() {
        return new ByteArrayInputStream(bos.toByteArray());
    }

    public byte[] getByteBos() {
        return bos.toByteArray();
    }

    public RatingDoc(String pib, Collection<Rating> ratings) {
        this.pib = pib;
        this.ratings = ratings;
    }

    public void createDoc(){
        try{
            XWPFDocument doc = new XWPFDocument();
            XWPFTable table;
            table = doc.createTable(ratings.size()+1, 13);
            CTTblPr tblPr = table.getCTTbl().getTblPr();
            CTString styleStr = tblPr.addNewTblStyle();
            styleStr.setVal("StyledTable");

            XWPFTableRow row1 = table.getRow(0);

            row1.getCell(0).setText("Предмет");
            row1.getCell(1). setText(pib);

            List<XWPFTableCell> cells = row1.getTableCells();
            // add content to each cell
            for (XWPFTableCell cell : cells) {
                // get a table cell properties element (tcPr)
                CTTcPr tcpr = cell.getCTTc().addNewTcPr();
                // set vertical alignment to "center"
                CTVerticalJc va = tcpr.addNewVAlign();
                va.setVal(STVerticalJc.CENTER);
                // create cell color element
                CTShd ctshd = tcpr.addNewShd();
                ctshd.setColor("auto");
                ctshd.setVal(STShd.CLEAR);
                ctshd.setFill("A7BFDE");
            }
            int i=1;
            for(Rating rating:ratings){
                XWPFTableRow row = table.getRow(i++);
                for (XWPFTableCell cell : cells) {
                    CTTcPr tcpr = cell.getCTTc().getTcPr();// .addNewTcPr();
                    // set vertical alignment to "center"
                    CTVerticalJc va = tcpr.getVAlign();// addNewVAlign();
                    va.setVal(STVerticalJc.CENTER);
                }
                row.getCell(0).setText(rating.getName());
                row.getCell(1).setText(rating.getR1()==0 ? ""  : String.format("%d", rating.getR1()));
                row.getCell(2).setText(rating.getR2()==0 ? ""  : String.format("%d", rating.getR2()));
                row.getCell(3).setText(rating.getR3()==0 ? ""  : String.format("%d", rating.getR3()));
                row.getCell(4).setText(rating.getR4()==0 ? ""  : String.format("%d", rating.getR4()));
                row.getCell(5).setText(rating.getR5()==0 ? ""  : String.format("%d", rating.getR5()));
                row.getCell(6).setText(rating.getR6()==0 ? ""  : String.format("%d", rating.getR6()));
                row.getCell(7).setText(rating.getR7()==0 ? ""  : String.format("%d", rating.getR7()));
                row.getCell(8).setText(rating.getR8()==0 ? ""  : String.format("%d", rating.getR8()));
                row.getCell(9).setText(rating.getR9()==0 ? ""  : String.format("%d", rating.getR9()));
                row.getCell(10).setText(rating.getR10()==0 ? ""  : String.format("%d", rating.getR10()));
                row.getCell(11).setText(rating.getR11()==0 ? ""  : String.format("%d", rating.getR11()));
                row.getCell(12).setText(rating.getR12()==0 ? ""  : String.format("%d", rating.getR12()));
            }

            int[] colunmWidths = new int[] {2600,300,300,300,300,300,300,300,300,300,300,300,300};
            for (int j=0; table.getRows().size()>j;j++) {
                XWPFTableRow row = table.getRow(0);
                for (int k=0; row.getTableCells().size()>k;k++) {
                    setColumnWidth(table, j, k, colunmWidths[k]);
                }
            }

            mergeCellHorizontally(table,0,1,12);

//            File file = new File("d:\\!\\"+filename);
            bos = new ByteArrayOutputStream();
            doc.write(bos);

        } catch (Exception e) {

        }
    }

    private void mergeCellHorizontally(XWPFTable table, int row, int fromCol, int toCol) {
        for (int colIndex = fromCol; colIndex <= toCol; colIndex++) {
            CTHMerge hmerge = CTHMerge.Factory.newInstance();
            if (colIndex == fromCol) {
                hmerge.setVal(STMerge.RESTART);
            } else {
                hmerge.setVal(STMerge.CONTINUE);
            }
            XWPFTableCell cell = table.getRow(row).getCell(colIndex);
            CTTcPr tcPr = cell.getCTTc().getTcPr();
            if (tcPr != null) {
                tcPr.setHMerge(hmerge);
            } else {
                tcPr = CTTcPr.Factory.newInstance();
                tcPr.setHMerge(hmerge);
                cell.getCTTc().setTcPr(tcPr);
            }
        }
    }

    private void setColumnWidth(XWPFTable table, int row, int col, int width) {
        CTTblWidth tblWidth = CTTblWidth.Factory.newInstance();
        tblWidth.setW(BigInteger.valueOf(width));
        tblWidth.setType(STTblWidth.DXA);
        CTTcPr tcPr = table.getRow(row).getCell(col).getCTTc().getTcPr();
        if (tcPr != null) {
            tcPr.setTcW(tblWidth);
        } else {
            tcPr = CTTcPr.Factory.newInstance();
            tcPr.setTcW(tblWidth);
            table.getRow(row).getCell(col).getCTTc().setTcPr(tcPr);
        }
    }

}
