package Test123;
import java.sql.*;
import java.util.*;
public class a {
	public static Connection con;
	public static Statement stmt;
	public static ResultSet rs;

	public static void main(String[] args) {
		try {
			System.out.println("====자리 배치 프로그램====");
			DB();
			STD();
		} catch (SQLException e) {
			try {
				DBRS();
				DB();
				STD();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	public static void STD() throws SQLException {
		Scanner in = new Scanner(System.in);
		while(true) {	
			int guess=1;
			do {
				System.out.printf("\n\n자리 출력(1) 자리 섞기(2) 저장 후 종료(3) 데이터베이스 초기화(4): ");
				try {
					guess=in.nextInt();
				} catch (Exception e) {
				}
				
				System.out.println();
				if(guess==1) { 					// 자리 출력
					String[] stds = new String[27];
					try {
						stds = DBreturn();
						for(int i=0; i<27; i++) {
							if(i%5==0 && i < 24) {
								System.out.printf("\n\n");
							} else if(i==24) {
								System.out.printf("\n\n");
							} System.out.printf("%10s",stds[i]);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if(guess==2) {           // 자리 섞기
					randing();
				} else if(guess==3) {           // 저장 후 종료
					try {
						DBS();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				} else if(guess==4 ) {
					DBRS();
				} else {
					System.out.println("다시 입력하세요");
				}
			} while(!(guess==1 || guess==2 || guess==3));	
		}
	}
	public static void DB() throws SQLException {
		Connection conn=null;
		Statement stmt=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		System.out.println("DB 메서드 실행");
		String url = "jdbc:mysql://127.0.0.1/?useSSL=true&user=root&password=1234&serverTimezone=Asia/Seoul";
		try {
			String SQL = "insert into stds values(?,?)";
			Class.forName("com.mysql.cj.jdbc.Driver");	
			conn = DriverManager.getConnection(url);
			stmt = conn.createStatement();
			pstmt = conn.prepareStatement(SQL);
			
			stmt.execute("use student");
			System.out.println("DB가 존재합니다.");
		} catch (Exception e) {
			System.out.println("DB가 존재하지 않습니다.");
			DBRS();
		}
	}
	
	public static void DBRS() throws SQLException {
		System.out.println("데이터베이스가 재생성 됩니다.");
		Connection conn=null;
		Statement stmt=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String url = "jdbc:mysql://127.0.0.1/?useSSL=true&user=root&password=1234&serverTimezone=Asia/Seoul";
		try {
			String SQL = "insert into stds values(?,?)";
			Class.forName("com.mysql.cj.jdbc.Driver");	
			conn = DriverManager.getConnection(url);
			stmt = conn.createStatement();
			pstmt = conn.prepareStatement(SQL);
			String str = 
					"강선민,강후민,공도영,김동현,김민서,김이루,김학성,박민성,박형준,심영훈,심지선,윤성민,이우진,이종현,전예준,정대한,조준영,최원하,최형준,홍지민,김경은,김지민,박솔,신가희,최지은,한세희,박두리";
			String Students[] = str.split(",");
			
			try {
				stmt.execute("DROP DATABASE STUDENT");
			} catch (Exception e) {}		
			stmt.execute("create database Student");
			stmt.execute("USE STUDENT");
			stmt.execute("create table stds(id int not null auto_increment primary key,name varchar(10) not null);");
			
			for(int i=0; i<Students.length; i++) {
				pstmt.setLong(1, i+1);
				pstmt.setString(2, Students[i]);
				pstmt.executeUpdate();
			}
		} catch(ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		} catch(SQLException se) {
			se.printStackTrace();
		}
	}

	public static void randing() {
		Connection conn=null;
		Statement stmt=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		System.out.println("랜덤 메서드 실행");
		String url = "jdbc:mysql://127.0.0.1/?useSSL=true&user=root&password=1234&serverTimezone=Asia/Seoul";
		try {
			String SQL = "insert into stds values(?,?)";
			Class.forName("com.mysql.cj.jdbc.Driver");	
			conn = DriverManager.getConnection(url);
			stmt = conn.createStatement();
			pstmt = conn.prepareStatement(SQL);
			
			stmt.execute("drop database student");
			stmt.execute("create database Student");
			stmt.execute("USE STUDENT");
			stmt.execute("create table stds(id int not null auto_increment primary key,name varchar(10) not null);");
			
			String str = 
					"강선민,강후민,공도영,김동현,김민서,김이루,김학성,박민성,박형준,심영훈,심지선,윤성민,이우진,이종현,전예준,정대한,조준영,최원하,최형준,홍지민,김경은,김지민,박솔,신가희,최지은,한세희,박두리";
			String Students[] = str.split(",");
			
			for(int i=0; i<Students.length; i++) {
				int rad=(int)(Math.random()*27);
				String temp = Students[i];
				Students[i] = Students[rad];
				Students[rad] = temp;
			}
			for(int i=0; i<Students.length; i++) {
				pstmt.setInt(1, i+1);
				pstmt.setString(2, Students[i]);
				pstmt.executeUpdate();
			}
		} catch (Exception e) {
			// TODO: handle exception
		} 
	}
	
	public static void DBS() throws SQLException {
		System.out.println("DB 저장 메서드 실행");
		System.out.println("====프로그램을 종료합니다.====");
		System.exit(0);	
	}
	
	public static String[] DBreturn() throws SQLException {
		try {
			Connection conn=null;
			Statement stmt=null;
			PreparedStatement pstmt=null;
			ResultSet rs = null;
			
			String url = "jdbc:mysql://127.0.0.1/?useSSL=true&user=root&password=1234&serverTimezone=Asia/Seoul";
			try {
				String SQL = "insert into stds values(?,?)";
				Class.forName("com.mysql.cj.jdbc.Driver");	
				conn = DriverManager.getConnection(url);
				stmt = conn.createStatement();
				pstmt = conn.prepareStatement(SQL);
				
				stmt.execute("use student");
				rs = stmt.executeQuery("select * from stds");
				
				int i=0; 
				String[] stds = new String[27];
				while (rs.next()) {
					stds[i]=rs.getString(2);
					i++;
				}
				return stds;
			} catch (Exception e) {
			} 
		} catch (Exception e) {
			return null;
		}
		return null;
	}

}
