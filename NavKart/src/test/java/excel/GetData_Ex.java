package excel;
 
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
 
public class GetData_Ex 
{
	public String[][] get_data_from_excel(String path, String sheetname) throws IOException
	{
		  FileInputStream stream = new FileInputStream(path);
		  Workbook workbook = WorkbookFactory.create(stream);
		  Sheet s = workbook.getSheet(sheetname);
		  int rowcount = s.getLastRowNum();
		  int cellcount = s.getRow(0).getLastCellNum();
		  String data[][] = new String[rowcount][cellcount];

	for (int i = 1; i <=rowcount; i++) 
	{
		   Row r = s.getRow(i);
		   for (int j = 0; j < cellcount; j++)
		   {
		   Cell c = r.getCell(j);
		try 
		{
			if (c.getCellType()==CellType.STRING)
			{
		      data[i-1][j] = c.getStringCellValue(); //i-1 meaning it will exclude header
			} 
			else
			{
		      data[i-1][j] = String.valueOf(c.getNumericCellValue());//i-1 meaning it will exclude header
			}
		} //try end
		catch (Exception e) 
		{
		     e.printStackTrace();
		}//catch end
		   }// for j end
	}// for i end
		  
	return data; 
	}//method end
	
}//end class