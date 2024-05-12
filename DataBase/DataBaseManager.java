package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class DataBaseManager {
    private static final String URL = "jdbc:mysql://localhost:3306/minigames";
    private static final String USERNAME = "Diane";
    private static final String PASSWORD = "J5T_/pg/G##u9~g";

    private static Connection connection;

    public DataBaseManager() {
        connection = getConnection();
    }

    // Méthode pour obtenir l'instance unique de la connexion
    public static Connection getConnection() {
        if (connection == null) {
            openConnection();
        }
        return connection;
    }

    // Méthode pour ouvrir la connexion à la base de données
    private static void openConnection() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connexion réussie à la base de données MySQL!");
        } catch (SQLException e) {
            System.out.println("Échec de la connexion à la base de données");
            e.printStackTrace();
        }
    }

    public void test() {
        try (Statement statement = connection.createStatement()) {
            // Créer la table "pute" si elle n'existe pas déjà
            String createTableSQL = "CREATE TABLE IF NOT EXISTS pute (id INT AUTO_INCREMENT PRIMARY KEY, name TEXT)";
            statement.executeUpdate(createTableSQL);

            System.out.println("Table 'pute' créée avec succès !");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la création de la table 'pute'");
            e.printStackTrace();
        }
    }
    public void CreateDBTrueFalse(){
        try (Statement statement = connection.createStatement()) {
            // Créer la table "pute" si elle n'existe pas déjà
            String createTableSQL = "CREATE TABLE IF NOT EXISTS TrueFalse (Id INT AUTO_INCREMENT PRIMARY KEY, Question TEXT,Answer TEXT)";
            // questionsReponses.put("Les chats sont des mammifères.", true);
            // questionsReponses.put("La Terre est plate.", false);
            // questionsReponses.put("Paris est la capitale de la France.", true);
            statement.executeUpdate(createTableSQL);

            System.out.println("Table 'pute' créée avec succès !");
            if (getRowCount("HangMan")==0){
                generateHangManWord();
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la création de la table 'pute'");
            e.printStackTrace();
        }
    }
    public static int getRowCount(String tableName) {
        String query = "SELECT COUNT(*) AS rowcount FROM " + tableName;
        try {
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                return rs.getInt("rowcount");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;  // Retourner 0 en cas d'échec
    }
    public void CreateDBHangman(){
        try (Statement statement = connection.createStatement()) {
            // Créer la table "pute" si elle n'existe pas déjà
            String createTableSQL = "CREATE TABLE IF NOT EXISTS HangMan (Id INT AUTO_INCREMENT PRIMARY KEY, Word TEXT)";
            statement.executeUpdate(createTableSQL);
            System.out.println("Table 'Hangman' créée avec succès !");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la création de la table 'pute'");
            e.printStackTrace();
        }
    }
    public void CreateDBBlackJack(){
        try (Statement statement = connection.createStatement()) {
            // Créer la table "pute" si elle n'existe pas déjà
            String createTableSQL = "CREATE TABLE IF NOT EXISTS BlackJack (Id INT AUTO_INCREMENT PRIMARY KEY, Pseudo TEXT,PassWord TEXT,Money INTEGER)";
            statement.executeUpdate(createTableSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void CreateDBSudoku(){
        try (Statement statement = connection.createStatement()) {
            // Créer la table "pute" si elle n'existe pas déjà
            String createTableSQL = "CREATE TABLE IF NOT EXISTS Sudoku (Id INT AUTO_INCREMENT PRIMARY KEY, Score Integer)";
            statement.executeUpdate(createTableSQL);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public int GetBestScore(){
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            // Créer un objet Statement à partir de la connexion globale
            statement = connection.createStatement();

            String sql = "SELECT * FROM Sudoku WHERE id = '"+1+"'"; 
            // Assurez-vous que le nom de la table est correct
            
            // Exécuter la requête et obtenir les résultats dans un ResultSet
            resultSet = statement.executeQuery(sql);
            
            // Parcourir le ResultSet et afficher les résultats
            while (resultSet.next()) {
                int score = resultSet.getInt("Score");
                return score;
            }
            return 0;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des questions");
            e.printStackTrace();
        }
        return -1;
    }
    public void SetScore(int score){
        String sql = "UPDATE Sudoku SET score = ?";

        try{
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, score);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Le score a été mis à jour avec succès.");
       } else {
           System.out.println("Aucune mise à jour effectuée. Vérifiez vos données.");
       }
   } catch (SQLException e) {
       e.printStackTrace();
   }
    }
    public void RegisterBJ(String pseudo,String mdp){
        try {
            String sql = "INSERT INTO BlackJack(Pseudo,PassWord,Money) VALUES (?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, pseudo);
            stmt.setString(2, mdp);
            stmt.setInt(3, 500);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static String hashPassword(String password) {
        try {
            // Création d'une instance de MessageDigest avec l'algorithme SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            
            // Mettre le mot de passe dans le digest pour le hachage
            byte[] hash = digest.digest(password.getBytes());
            
            // Convertir le hachage byte en format hexadécimal
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            // Gérer l'exception NoSuchAlgorithmException
            e.printStackTrace();
            return null;
        }
    }
    public void AddQuestion(String Question,Boolean Answer){

        String stringanswer;
        if(Answer){
            stringanswer = "true";
        }else {
            stringanswer = "false";
        }
        Statement statement = null;
        try {
            statement = connection.createStatement();
            System.out.println("add question :"+Question+ " | "+stringanswer);
            String sql = "INSERT INTO TrueFalse(Question,Answer) VALUES('"+ Question + "','"+stringanswer+"')";

            System.out.println(sql);
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Map<String, Boolean> GetQuestion(){
        Statement statement = null;
        ResultSet resultSet = null;
        Map<String, Boolean> questionsReponses = new HashMap<>();
        try {
            // Créer un objet Statement à partir de la connexion globale
            statement = connection.createStatement();

            String sql = "SELECT * FROM TrueFalse"; 
            // Assurez-vous que le nom de la table est correct
            
            // Exécuter la requête et obtenir les résultats dans un ResultSet
            resultSet = statement.executeQuery(sql);
            
            // Parcourir le ResultSet et afficher les résultats
            while (resultSet.next()) {
                String question = resultSet.getString("Question");
                String answer = resultSet.getString("Answer");
                boolean answerbool = true;
                if (answer.equals("false")){
                    answerbool = false;
                }
                questionsReponses.put(question,answerbool);
                
            }
            return questionsReponses;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des questions");
            e.printStackTrace();
        } finally {
            // Fermer les ressources
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return new HashMap<>();
    }
    public int Login(String pseudo , String Password){
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            // Créer un objet Statement à partir de la connexion globale
            statement = connection.createStatement();

            String sql = "SELECT * FROM BlackJack WHERE pseudo = '"+pseudo+"'"; 
            // Assurez-vous que le nom de la table est correct
            
            // Exécuter la requête et obtenir les résultats dans un ResultSet
            resultSet = statement.executeQuery(sql);
            
            // Parcourir le ResultSet et afficher les résultats
            while (resultSet.next()) {
                String password = resultSet.getString("PassWord");
                Integer money = resultSet.getInt("Money");
                System.out.println(Password+" | "+password);
                if(Password.equals(password)){
                    return money;
                }
                return -1;
                
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des questions");
            e.printStackTrace();
        }
        return -1;
    }
    public void AddWord(String word){
        Statement statement = null;
        try {
            statement = connection.createStatement();
            String sql = "INSERT INTO HangMan(Word) VALUES('"+ word + "')";

            System.out.println(sql);
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void SetMoney(String pseudo, int nouvelleSomme) {
        String sql = "UPDATE BlackJack SET Money = ? WHERE Pseudo = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
       
            // Définir les paramètres de la requête
            statement.setInt(1, nouvelleSomme);
            statement.setString(2, pseudo);
            
            // Exécuter la requête de mise à jour
            int lignesModifiees = statement.executeUpdate();
            
            // Vérifier si des lignes ont été modifiées
            if (lignesModifiees > 0) {
                System.out.println("La somme d'argent du joueur " + pseudo + " a été mise à jour avec succès.");
            } else {
                System.out.println("Aucun joueur trouvé avec le pseudo " + pseudo + ".");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int GetMoney(String pseudo){
        String sql = "SELECT Money FROM BlackJack WHERE Pseudo = ?";
        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, pseudo);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("Money");
            } else {
                return -1;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }
    public List<String> GetHangManWords(){
        Statement statement = null;
        ResultSet resultSet = null;
        List<String> Words = new ArrayList<String>() ;
        try {
            // Créer un objet Statement à partir de la connexion globale
            statement = connection.createStatement();

            String sql = "SELECT * FROM HangMan"; 
            // Assurez-vous que le nom de la table est correct
            
            // Exécuter la requête et obtenir les résultats dans un ResultSet
            resultSet = statement.executeQuery(sql);
            
            // Parcourir le ResultSet et afficher les résultats
            while (resultSet.next()) {
                String word = resultSet.getString("Word");
                Words.add(word);
                
            }
            return Words;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des questions");
            e.printStackTrace();
        } finally {
            // Fermer les ressources
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<String>();
    }

    public int getTableSize(String tableName) throws SQLException {
        int size = 0;
        Statement statement = null;
        ResultSet resultSet = null;
    
        try {
            // Créer un objet Statement à partir de la connexion globale
            statement = connection.createStatement();
    
            // Requête SQL pour compter le nombre de lignes dans la table spécifiée
            String sql = "SELECT COUNT(*) as count FROM " + tableName;
    
            // Exécuter la requête et obtenir les résultats dans un ResultSet
            resultSet = statement.executeQuery(sql);
    
            // Récupérer la taille de la table à partir du ResultSet
            if (resultSet.next()) {
                size = resultSet.getInt("count");
            }
        } finally {
            // Fermer les ressources
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        }
    
        return size;
    }
    public void generateQuestions() {
        String AddQuestion = "Insert into TrueFalse(Question,Answer) Values(?,?)";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(AddQuestion);
            preparedStatement.setString(1, "Les chats sont des mammifères.");
            preparedStatement.setString(2, "true");
            preparedStatement.executeUpdate();
            preparedStatement.setString(1, "La Terre est plate.");
            preparedStatement.setString(2, "false");
            preparedStatement.executeUpdate();
            preparedStatement.setString(1, "Paris est la capitale de la France.");
            preparedStatement.setString(2, "true");
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    public void generateHangManWord() {
        String AddQuestion = "Insert into Hangman(Word) Values(?)";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(AddQuestion);
            preparedStatement.setString(1, "pendu");
            preparedStatement.executeUpdate();
            preparedStatement.setString(1, "chance");;
            preparedStatement.executeUpdate();
            preparedStatement.setString(1, "programmation");
            preparedStatement.executeUpdate();
            preparedStatement.setString(1, "android");
            preparedStatement.executeUpdate();
            preparedStatement.setString(1, "studio");
            preparedStatement.executeUpdate();
            preparedStatement.setString(1, "ratio");
            preparedStatement.executeUpdate();
            preparedStatement.setString(1, "pute");
            preparedStatement.executeUpdate();
            preparedStatement.setString(1, "singe");
            preparedStatement.executeUpdate();
            
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    
}
