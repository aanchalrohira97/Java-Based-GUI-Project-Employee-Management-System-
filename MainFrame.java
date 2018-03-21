import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;  //use drivermanager,ql,statement

class MainFrame extends JFrame
{
Container c;
JButton btnAdd,btnView,btnUpdate,btnDelete;

MainFrame()
{
c= getContentPane();
c.setLayout(new FlowLayout());

btnAdd = new JButton("Add");  //buttons instantiated
btnView= new JButton("View");
btnUpdate= new JButton("Update");
btnDelete = new JButton("Delete");

c.add(btnAdd);
c.add(btnView);
c.add(btnUpdate);
c.add(btnDelete);

btnAdd.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent ae)
{
AddFrame a= new AddFrame();
dispose();
}
} );

btnView.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent ae)
{
ViewFrame a= new ViewFrame();
dispose();
}
} );


btnUpdate.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent ae)
{
UpdateFrame a= new UpdateFrame();
dispose();
}
} );

btnDelete.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent ae)
{
DeleteFrame a= new DeleteFrame();
dispose();
}
} );

setSize(500,150);
setLocationRelativeTo(null); //sets the frame in the centre
setTitle("Employee Management System");
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}

public static void main(String args[])
{

MainFrame m= new MainFrame();
}//end of main

}//end of class

class DatabaseHandler 
{
static Connection con;
static void getCon()
{
try
{
DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","abc123");
}
catch(SQLException e)
{
System.out.println(e);
}
}

public void addEmployee(int id,String s)
{
getCon();
try
{
String sql="insert into employee values(?,?)";
PreparedStatement pst= con.prepareStatement(sql);
pst.setInt(2,id);
pst.setString(1,s);
int r=pst.executeUpdate();
JOptionPane.showMessageDialog(new JDialog(), r+"recoreds inserted");
}
catch(SQLException e)
{
JOptionPane.showMessageDialog(new JDialog(),"Insert issue");
}
}

public String viewEmployee()
{
getCon();
StringBuffer sb=new StringBuffer();
try
{
Statement s1=con.createStatement();
String s2="select * from employee";
ResultSet rs=s1.executeQuery(s2);
while(rs.next())
{
int id=rs.getInt(2);
String name=rs.getString(1);
sb.append("Id:" + id + "Name" + name + "\n");
}}
catch(SQLException e) {}
return sb.toString();
}

public void updateEmployee(int id,String s)
{
getCon();
try
{
String query = "update employee set ename = ? where eid = ?";
      PreparedStatement preparedStmt = con.prepareStatement(query);
      preparedStmt.setInt   (2, id);
      preparedStmt.setString(1,s);
    int r=  preparedStmt.executeUpdate();
	if(r==0)
{
JOptionPane.showMessageDialog(new JDialog(),"invalid entry");
}      
else
{
      con.close();
JOptionPane.showMessageDialog(new JDialog(),"records Updated");
}}

catch(SQLException e)
{
JOptionPane.showMessageDialog(new JDialog(),"Update issue");
}
}

public void deleteEmployee(int id)
{
getCon();
try
{
String sql="delete from employee where eid=?";
PreparedStatement pst= con.prepareStatement(sql);
pst.setInt(1,id);
int r=pst.executeUpdate();
if(r==0)
{
JOptionPane.showMessageDialog(new JDialog(),"invalid entry");
}      
else
{
      con.close();
JOptionPane.showMessageDialog(new JDialog(),r+"records Deleted");
}}
catch(SQLException e)
{
JOptionPane.showMessageDialog(new JDialog(),"delete issue");
}
}

}// end of class DatabaseHandler 





























