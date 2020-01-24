import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class StudentDAO {
	static Connection currentCon = null;
    static ResultSet rs = null;
    
    public static ArrayList<DataChart> getData(){
    	ArrayList<DataChart> list = new ArrayList<DataChart>();
    	DataChart slice;
    	Statement stmt = null;
    	
    	String searchQuery = "select facultate, COUNT(idStudent) as numar from student GROUP BY facultate";
    	
    	try {
            currentCon = ConnectionManager.getConnection();
            stmt = currentCon.createStatement();
            rs = stmt.executeQuery(searchQuery);

            while (rs.next()) {
            	slice = new DataChart();
            	
            	String facultate = rs.getString("facultate");
            	int numar = rs.getInt("numar");
            	
            	slice.setEticheta(facultate);
            	slice.setNumar(numar);
            	
            	list.add(slice);
            }
    	} catch (Exception e) {
            System.out.println(e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                }
                rs = null;
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (Exception e) {
                }
                stmt = null;
            }
            if (currentCon != null) {
                try {
                    currentCon.close();
                } catch (Exception e) {
                }
                currentCon = null;
            }
        }
    	
    	return list;
    }
}
