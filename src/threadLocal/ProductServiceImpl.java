package threadLocal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProductServiceImpl implements ProductService {
    private static final String update = null;
    private static final String insert = null;

    @Override
    public void updateProductPrice(long productId, int price) {
        try {
            Connection connection = DBUtils.getConnection();
            connection.setAutoCommit(false);

            updateProduct(connection, update, productId, price);
            insert(connection, insert, "create product.");
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeConnection();
        }
    }

    private void insert(Connection connection, String insert, String s) throws Exception {
        PreparedStatement preparedStatement = connection.prepareStatement(insert);
        preparedStatement.setString(1, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(new Date()));
        preparedStatement.setString(2, s);
        int rows = preparedStatement.executeUpdate();
        if (rows != 0)
            System.out.println("Insert log success!");
    }


    private void updateProduct(Connection connection, String update, long productId, int price) throws Exception {
        PreparedStatement preparedStatement = connection.prepareStatement(update);
        preparedStatement.setInt(1, price);
        preparedStatement.setLong(2, productId);
        int rows = preparedStatement.executeUpdate();
        if (rows != 0)
            System.out.println("update product success!");
    }


}
