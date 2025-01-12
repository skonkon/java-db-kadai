package kadai_007;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Posts_Chapter07 {
    public static void main(String[] args) {
    	
    	Connection con = null;
        Statement statement = null;
        
    	
        try {
            // データベースに接続
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost/challenge_java",
                "root",
                "Nekonoko428"
            );

            System.out.println("データベース接続成功");
            
            // SQLクエリを実行するためのStatementを作成
            Statement st = con.createStatement();


            String sqlInsert = "INSERT INTO posts (user_id, posted_at, post_content, likes) VALUES "
                    + "(1003, '2023-02-08', '昨日の夜は徹夜でした・・', 13),"
                    + "(1002, '2023-02-08', 'お疲れ様です！', 12),"
                    + "(1003, '2023-02-09', '今日も頑張ります！', 18),"
                    + "(1001, '2023-02-09', '無理は禁物ですよ！', 17),"
                    + "(1002, '2023-02-10', '明日から連休ですね！', 20);";
  
            int rowsInserted = st.executeUpdate(sqlInsert);
            System.out.println(rowsInserted + "件のレコードが追加されました");

    
            int userId = 1002;

           
            System.out.println("ユーザーIDが" + userId + "のレコードを検索しました");

    
            Statement selectStatement = con.createStatement();
 
            String selectSql = "SELECT * FROM posts WHERE user_id = " + userId + ";";
            ResultSet resultSet = selectStatement.executeQuery(selectSql);

            while(resultSet.next()) {
                int resultUserId = resultSet.getInt("user_id");
                String posted_at = resultSet.getString("posted_at");
                String post_content = resultSet.getString("post_content");
                int likes = resultSet.getInt("likes");
                System.out.println("ユーザーID=" + resultUserId + "、" + resultSet.getRow() + "件目：投稿日時=" + posted_at
                    + "／投稿内容=" + post_content + "／いいね数=" + likes );
            }
            }
             
             catch(SQLException e) {
            System.out.println("エラー発生：" + e.getMessage());
        } finally {
      
            if( statement != null ) {
                try { statement.close(); } catch(SQLException ignore) {}
            }
            if( con != null ) {
                try { con.close(); } catch(SQLException ignore) {}
            }
            
        }
	}
}