import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class AddFrame extends JFrame
{
Container c;
JLabel lblId,lblName;
JTextField txtId,txtName;
JButton btnSave,btnBack;
JPanel p1,p2;

AddFrame()
{
c=getContentPane();

p1=new JPanel();
lblId=new JLabel("id:");
txtId=new JTextField(5);
lblName= new JLabel("Name");
txtName=new JTextField(10);

p1.add(lblId);
p1.add(txtId);
p1.add(lblName);
p1.add(txtName);

c.add(p1);

p2=new JPanel();
btnSave = new JButton("Save");
btnBack = new JButton("Back");
p2.add(btnSave);
p2.add(btnBack);

btnBack.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent ae)
{
MainFrame a= new MainFrame();
dispose();
}
} );

c.add("South",p2);

btnSave.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent ae)
{
String i= txtId.getText();
String n=txtName.getText();
if(i.length()== 0)
{
JOptionPane.showMessageDialog(new JDialog(),"Id is empty");
txtId.requestFocus();
return;
}
if(n.length()== 0)
{
JOptionPane.showMessageDialog(new JDialog(),"Name is empty");
txtName.requestFocus();
return;
}

DatabaseHandler d=new DatabaseHandler();
d.addEmployee(Integer.parseInt(i),n);
txtId.setText("");
txtName.setText("");
txtId.requestFocus();
}});


setTitle("Add Employee");
setSize(500,150);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);


}

public static void main(String args[])
{
AddFrame a=new AddFrame();
}
}
