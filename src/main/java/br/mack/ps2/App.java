package br.mack.ps2;
import java.sql.*;
import java.util.Scanner;

public class App
{
    public static void main( String[] args ) {

        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            String db="ex_prog2";
            String url="jdbc:mysql://localhost:3306/"+db+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String user="root";
            String psw="";
            conn=DriverManager.getConnection(url, user, psw);

            menu();
            int x = escolhe();
            while(x !=0){
                if(x==0) {
                    System.out.println("Conexão Encerrada!!!");
                    menu();
                    x = escolhe();
                }
                else if(x==1){
                    String sql = "INSERT INTO contato(TIA, horaEntrada, horaSaida) VALUES(?,?,?)";
                    PreparedStatement stmt = conn.prepareStatement(sql);

                    Scanner input = new Scanner(System.in);
                    System.out.println("Digite o TIA: ");
                    String d = input.nextLine();
                    System.out.println("Digite a entrada: ");
                    String y = input.nextLine();
                    System.out.println("Digite a saída: ");
                    String z = input.nextLine();

                    stmt.setString(1, d);
                    stmt.setString(2, y);
                    stmt.setString(3, z);
                    stmt.execute();
                    stmt.close();
                    menu();
                    x = escolhe();
                }
                else if(x==2){
                    String sql = "Select * FROM contato";
                    PreparedStatement pstm = conn.prepareStatement(sql);
                    ResultSet rs = pstm.executeQuery();
                    while (rs.next()) {
                        String TIA = rs.getString("TIA");
                        String horaEntrada = rs.getString("horaEntrada");
                        String horaSaida = rs.getString("horaSaida");
                        System.out.println("TIA: " + TIA + " - "+ "Horário de Entrada: " + horaEntrada + " - " + "Horário de Saída: " + horaSaida);
                    }rs.close();
                    menu();
                    x = escolhe();
                }
            }conn.close();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("SQL EXCEPTION");
            e.printStackTrace();
        }
    }
    public static void menu(){
        System.out.println(" ");
        System.out.println("Tabela de Frequência: ");
        System.out.println("Para inserir na tabela digite 1: ");
        System.out.println("Para consultar a tabela digite 2: ");
        System.out.println("Para encerrar conexão digite 0: ");
    }
    public static int escolhe(){
        Scanner input = new Scanner(System.in);
        System.out.println("Selecione a opção: ");
        int x = input.nextInt();
        return x;
    }
}