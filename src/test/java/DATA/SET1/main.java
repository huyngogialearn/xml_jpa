package DATA.SET1;

import annotation_process.user_annotaiton.database.Database;
import e.DatabaseStrategy;
import e.SQLType;

@Database(name = "Demo1",url = "jdbc:mysql://localhost:3306/",username = "sa",password = "123456",strategy = DatabaseStrategy.CREATE, type = SQLType.MySQL)
public class main {

}
