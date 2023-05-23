package ActorDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.zkoss.zk.ui.util.Notification;

public class ActorController {

	String var_connection = "jdbc:mysql://localhost:3306/sakila?user=root&password=admin";
	String var_driver = "com.mysql.jdbc.Driver";

	Connection cn = null;
	Statement st = null;
	ResultSet rs = null;

	public ArrayList<Actor> getallActor() {
		ArrayList<Actor> al = new ArrayList<>();

		String qry = "SELECT * FROM actor";

		try {
			System.out.println("Loading JDBC driver...");
			Class.forName(var_driver);
			cn = DriverManager.getConnection(var_connection);
			st = cn.createStatement();
			rs = st.executeQuery(qry);
			while (rs.next()) {
				Actor a = new Actor(rs.getString("first_name"), rs.getString("last_name"), rs.getDate("last_update"));
				a.setActor_id(rs.getInt("actor_id"));
				al.add(a);

			}
		} catch (Exception ex) {
			Notification.show(ex.toString());
		} finally {
			try {
				if (null != rs)
					rs.close();
			} catch (Exception ex) {
			}
			try {
				if (null != st)
					st.close();
			} catch (Exception ex) {
			}
			try {
				if (null != cn)
					cn.close();
			} catch (Exception ex) {
			}
		}
		return al;
	}

	public String updateData(Actor selectedActor) {
		String result = null;
		Connection cn = null;
		PreparedStatement ps1 = null;

		try {
			Class.forName(var_driver);
			cn = DriverManager.getConnection(var_connection);

			ps1 = cn.prepareStatement("update actor set first_name = ? ,last_name = ?  where actor_id = ?");

			ps1.setString(1, selectedActor.getFirst_name());
			ps1.setString(2, selectedActor.getLast_name());
			ps1.setInt(3, selectedActor.getActor_id());

			ps1.executeUpdate();

			result = "Schauspielerdaten erfolgreich geändert";

		} catch (Exception ex) {
			result = ex.toString();
		} finally {
			try {
				if (null != ps1)
					ps1.close();
			} catch (Exception ex) {
			}
			try {
				if (null != cn)
					cn.close();
			} catch (Exception ex) {
			}
		}
		return result;
	}

	public String createNewActor() {
		String result = null;
		Connection cn = null;
		PreparedStatement ps1 = null;

		try {
			Class.forName(var_driver);
			cn = DriverManager.getConnection(var_connection);

			ps1 = cn.prepareStatement(" insert into actor(first_name) Values(?)");
			ps1.setString(1, "Neuer Eintrag");

			ps1.executeUpdate();

			result = "Schauspieler erfolgreich hinzugefügt";

		} catch (Exception ex) {
			result = ex.toString();
		} finally {
			try {
				if (null != ps1)
					ps1.close();
			} catch (Exception ex) {
			}
			try {
				if (null != cn)
					cn.close();
			} catch (Exception ex) {
			}
		}
		return result;

	}


	public ArrayList<Customer> getallCustomer() {
		ArrayList<Customer> cl = new ArrayList<>();

		String qry = "SELECT * FROM customer";

		try {
			Class.forName(var_driver);
			cn = DriverManager.getConnection(var_connection);
			st = cn.createStatement();
			rs = st.executeQuery(qry);
			while (rs.next()) {
				Customer c = new Customer(rs.getString("first_name"), rs.getString("last_name"), rs.getString("email"));
				c.setCustomer_id(rs.getInt("customer_id"));
				cl.add(c);

			}
		} catch (Exception ex) {
			Notification.show(ex.toString());
		} finally {
			try {
				if (null != rs)
					rs.close();
			} catch (Exception ex) {
			}
			try {
				if (null != st)
					st.close();
			} catch (Exception ex) {
			}
			try {
				if (null != cn)
					cn.close();
			} catch (Exception ex) {
			}
		}
		return cl;
	}


//	public String showStatistics() {
//		String result = null;
//		Connection cn = null;
//		PreparedStatement ps1 = null;
//
//		try {
//			Class.forName(var_driver);
//			cn = DriverManager.getConnection(var_connection);
//
//			ps1 = cn.prepareStatement(" SELECT MAX(amount) max_amt,\r\n"
//					+ "MIN(amount) min_amt,\r\n"
//					+ "AVG(amount) avg_amt,\r\n"
//					+ "SUM(amount) tot_amt,\r\n"
//					+ "COUNT(*) num_payments\r\n"
//					+ "FROM payment;");
//			ps1.setString(1, "Neuer Eintrag");
//
//			ps1.executeUpdate();
//
//			result = "Schauspieler erfolgreich hinzugefügt";
//
//		} catch (Exception ex) {
//			result = ex.toString();
//		} finally {
//			try {
//				if (null != ps1)
//					ps1.close();
//			} catch (Exception ex) {
//			}
//			try {
//				if (null != cn)
//					cn.close();
//			} catch (Exception ex) {
//			}
//		}
//		return result;
//
//	}


//	public ArrayList<Stats> getStats() {
//		ArrayList<Stats> sl = new ArrayList<>();
//
//		String qry = "SELECT MAX(amount) max_amt,\r\n"
//				+ "MIN(amount) min_amt,\r\n"
//				+ "AVG(amount) avg_amt,\r\n"
//				+ "SUM(amount) tot_amt,\r\n"
//				+ "COUNT(*) num_payments\r\n"
//				+ "FROM payment;";
//
//		try {
//			Class.forName(var_driver);
//			cn = DriverManager.getConnection(var_connection);
//			st = cn.createStatement();
//			rs = st.executeQuery(qry);
//			while (rs.next()) {
//				Stats s = new Stats(rs.getDouble("max_amt");
//				s.setActor_id(rs.getInt("actor_id"));
//				sl.add(s);
//
//			}
//		} catch (Exception ex) {
//			Notification.show(ex.toString());
//		} finally {
//			try {
//				if (null != rs)
//					rs.close();
//			} catch (Exception ex) {
//			}
//			try {
//				if (null != st)
//					st.close();
//			} catch (Exception ex) {
//			}
//			try {
//				if (null != cn)
//					cn.close();
//			} catch (Exception ex) {
//			}
//		}
//		return al;
//	}




}
