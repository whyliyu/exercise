
import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLAlterTableAddColumn;
import com.alibaba.druid.sql.ast.statement.SQLAlterTableItem;
import com.alibaba.druid.sql.ast.statement.SQLAlterTableStatement;
import com.alibaba.druid.sql.ast.statement.SQLColumnDefinition;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlAlterTableChangeColumn;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlAlterTableModifyColumn;
import com.alibaba.druid.util.JdbcConstants;

import java.util.List;

public class Test {

    public static void main(String[] args) {
        new Test().test();
    }

    public void test() {
        final String dbType = JdbcConstants.MYSQL; // 可以是ORACLE、POSTGRESQL、SQLSERVER、ODPS等
        String sql = "alter table `myTable` add (`int` FIXED(5) NOT NULL,int2 bit(32)),CHANGE column `old_col_name` `new_col_name` INT after c,modify COLUMN `bit`  INT,alter `z` DROP DEFAULT,drop `dro`";
        String sql1 = "alter table myTable CHANGE column old_col_name new_col_name INT";;
        System.out.println(sql.substring(77));
        List<SQLStatement> stmtList = SQLUtils.parseStatements(sql, dbType);
        for(SQLStatement st : stmtList) {
            System.out.println("ast:" + st.getClass());
            SQLAlterTableStatement statement = (SQLAlterTableStatement) st;
            List<SQLAlterTableItem> items = statement.getItems();
            for(SQLAlterTableItem item : items) {
                if(item instanceof SQLAlterTableAddColumn) {
                    System.out.println(".... " + item.getClass());
                    SQLAlterTableAddColumn addColumn = (SQLAlterTableAddColumn) item;
                    List<SQLColumnDefinition> columns = addColumn.getColumns();
                    for(SQLColumnDefinition column : columns) {
                        System.out.println(column.getName().getSimpleName());
                    }

                }else if(item instanceof MySqlAlterTableChangeColumn) {
                    System.out.println("change :" + item.toString());
                    System.out.println(((MySqlAlterTableChangeColumn)item).getColumnName());
                    System.out.println(((MySqlAlterTableChangeColumn)item).getNewColumnDefinition().getName());
                }else if(item instanceof MySqlAlterTableModifyColumn) {
                    System.out.println("modify :" + item.toString());
                    System.out.println(((MySqlAlterTableModifyColumn)item).getNewColumnDefinition().getName());
                   // System.out.println(((MySqlAlterTableModifyColumn)item).get);
                }else {
                    System.out.println("other " + item.getClass());
                }
            }
        }

    }
}
