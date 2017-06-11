import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

import oracle.jdbc.pool.OracleDataSource;

public class DbHandler {
	public Connection getdbconwithoracle() {
		Connection con = null;

		try {

			OracleDataSource ods = new OracleDataSource();
			ods.setURL("jdbc:oracle:thin:@localhost:1521:xe");
			con = ods.getConnection("generalStore", "password");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("connection established");
		return con;
	}

	public int getTotalBill(int id, int no) {
		Connection con1 = getdbconwithoracle();
		int bill = 0;
		try {
			System.out.println(id);
			PreparedStatement stmt = con1.prepareStatement("select no from items where itemid=?");

			stmt.setInt(1, id);
			ResultSet rset = stmt.executeQuery();
			if (rset.next()) {
				int stock = rset.getInt("no");
				System.out.println(stock);
				System.out.println(no);

				if (stock >= no) {
					PreparedStatement stmt2 = con1.prepareStatement("select * from items where itemid=?");
					stmt2.setInt(1, id);
					System.out.println(id);
					ResultSet rste = stmt2.executeQuery();
					if (rste.next()) {
						int price = rste.getInt("PRICE");
						bill = price * no;
					}
					rset.close();
					stmt.close();

					rste.close();
					stmt2.close();
					stock = stock - no;
					PreparedStatement stmt21 = con1.prepareStatement("update items set no=? where itemid=?  ");
					stmt21.setInt(1, stock);
					stmt21.setInt(2, id);
					ResultSet rSet = stmt21.executeQuery();

					rSet.close();
					stmt21.close();
					con1.close();
					
					System.out.println("Your Bill is :- ");

				} else {
					System.out.println("Item not available in stock");
				}

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bill;
	}
}