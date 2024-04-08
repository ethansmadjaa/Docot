public class DaoImpl implements ProductDao{

    private Connection conn;
    private Statement stmt;
    private ResultSet rset;
    private ResultSetMetaData rsetMeta;

    public ProductDaoImpl(Connection conn, Statement stmt,ResultSet rset, ResultSetMetaData rsetMeta) {
        this.conn = conn;
        this.stmt = stmt;
        this.rset = rset;
        this.rsetMeta = rsetMeta;
    }

    public ProductDaoImpl() {}

    @Override
    public void connect(String URLDataBase, String LoginDataBase, String PwdDataBase) throws SQLException, ClassNotFoundException {
        // chargement driver "com.mysql.jdbc.Driver"
        Class.forName("com.mysql.jdbc.Driver");

        //création d'une connexion JDBC à la base
        conn = DriverManager.getConnection(URLDataBase, LoginDataBase, PwdDataBase);

        // création d'un ordre SQL (statement)
        stmt = conn.createStatement();
    }

    @Override
    public void disconnect() throws SQLException {
        conn.close();
    }
