import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

class Connect{
  Connection OpenCon(){
    try{
      Class.forName("com.mysql.jdbc.Driver");
      Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/hk","root","root");
      return c;
    }
    catch(Exception e){
      System.out.println(e);
    }
    return null;
  }
}

class Insert extends JFrame implements ActionListener{
  JTextField t1,t2,t3,t4;
  JButton b1;
  Insert(){
    t1=new JTextField("Enter Name");
    t2=new JTextField("Enter Section");
    t3=new JTextField("Enter Roll No.");
    t4=new JTextField("Enter Year");
    b1=new JButton("Insert Values");
    t1.setBounds(20,60,300,40);
    t2.setBounds(20,110,300,40);
    t3.setBounds(20,160,300,40);
    t4.setBounds(20,210,300,40);
    b1.setBounds(30,260,200,40);
    add(t1);
    add(t2);
    add(t3);
    add(t4);
    add(b1);
    setLayout(null);
    b1.addActionListener(this);
    setSize(400,400);
    setVisible(true);
  }
  public void actionPerformed(ActionEvent ae)
  {
    String a = t1.getText();
    String b = t2.getText();
    int d=Integer.parseInt(t3.getText());
    int f=Integer.parseInt(t4.getText());
    try{
      Connect obj=new Connect();
      Connection c=obj.OpenCon();
      Statement s=c.createStatement();
      s.executeUpdate("insert into student values('"+a+"',"+d+",'"+b+"',"+f+")");
      System.out.println("Values Inserted");
      c.close();
      dispose();
    }
    catch(Exception e){
      System.out.println(e);
    }
  }
}

class Update extends JFrame implements ActionListener{
  JTextField t1,t2,t3;
  JButton b1;
  Update(){
    t2=new JTextField("Enter Updated Section");
    t3=new JTextField("Enter Roll No.");
    b1=new JButton("Update Values");
    t2.setBounds(20,110,300,40);
    t3.setBounds(20,160,300,40);
    b1.setBounds(30,210,200,40);
    add(t2);
    add(t3);
    add(b1);
    setLayout(null);
    b1.addActionListener(this);
    setSize(400,400);
    setVisible(true);
  }
  public void actionPerformed(ActionEvent ae){
    String sec = t2.getText();
    int ro=Integer.parseInt(t3.getText());
    try{
      Connect obj=new Connect();
      Connection c=obj.OpenCon();
      Statement s=c.createStatement();
      s.executeUpdate("update student set section = '"+sec+"' where roll_no='"+ro+"'");
      System.out.println("Values Updated");
      c.close();
      dispose();
    }
    catch(Exception e){
      System.out.println(e);
    }
  }
}

class Display extends JFrame implements ActionListener{
  JTextField t1;
  JButton b1;
  JTable jt;
  Display(){
    t1=new JTextField("Enter Name");
    b1=new JButton("Search Values");
    t1.setBounds(20,60,300,40);
    b1.setBounds(30,110,200,40);
    add(t1);
    add(b1);
    setLayout(null);
    b1.addActionListener(this);
    setSize(400,400);
    setVisible(true);
  }
  public void actionPerformed(ActionEvent ae){
    String nam = t1.getText();
    new SearchRes(nam);
    dispose();
  }
}

class SearchRes extends JFrame{
  JTable jt;
  SearchRes(String nam){
    jt=new JTable();
    jt.setBounds(30,60,300,200);
    add(jt);
    try{
      Connect obj=new Connect();
      Connection c=obj.OpenCon();
      Statement s=c.createStatement();
      ResultSet rs=s.executeQuery("select * from student where name='"+nam+"'");
      DefaultTableModel model = new DefaultTableModel(new String[]{"Name", "Roll No", "Section"}, 0);
      while(rs.next()){
        model.addRow(new Object[]{rs.getString(1),rs.getInt(2),rs.getString(3)});
      }
      jt.setModel(model);
      c.close();
    }
    catch(Exception e){
      System.out.println(e);
    }
    setLayout(null);
    setSize(400,400);
    setVisible(true);
  }
}

class Promote extends JFrame implements ActionListener{
  JLabel l1,l2;
  JComboBox cb,cb1;
  JButton b1;
  Promote(){
    String years[]={"1","2","3"};
    String sections[]={"A","B","C","D","E","F","G","H","I","J","K"};
    cb=new JComboBox(years);
    cb1=new JComboBox(sections);
    l1=new JLabel("Select Year:");
    l2=new JLabel("Select Section:");
    b1=new JButton("Promote");
    l1.setBounds(20,60,300,40);
    cb.setBounds(20,110,300,40);
    l2.setBounds(20,160,300,40);
    cb1.setBounds(20,210,300,40);
    b1.setBounds(30,260,200,40);
    add(l1);
    add(cb);
    add(l2);
    add(cb1);
    add(b1);
    setLayout(null);
    b1.addActionListener(this);
    setSize(400,400);
    setVisible(true);
  }
  public void actionPerformed(ActionEvent ae){
    String yearr = ""+cb.getItemAt(cb.getSelectedIndex());
    String sect = ""+cb1.getItemAt(cb1.getSelectedIndex());
    int yeaar=Integer.parseInt(yearr);
    int yeaaar=yeaar+1;
    try{
      Connect obj=new Connect();
      Connection c=obj.OpenCon();
      Statement s=c.createStatement();
      s.executeUpdate("update student set year="+yeaaar+" where section='"+sect+"' and year="+yeaar);
      System.out.println("Values Updated");
      c.close();
      dispose();
    }
    catch(Exception e){
      System.out.println(e);
    }
  }
}
public class DbCon extends JFrame implements ActionListener{
  JButton b1,b2,b3,b4;
  DbCon(){
    b1=new JButton("Add a Student");
    b2=new JButton("Update Data");
    b3=new JButton("Search a Student");
    b4=new JButton("Promote all Students");
    b1.setBounds(20,60,300,40);
    b2.setBounds(20,110,300,40);
    b3.setBounds(20,160,300,40);
    b4.setBounds(20,210,300,40);
    add(b1);
    add(b2);
    add(b3);
    add(b4);
    setLayout(null);
    b1.addActionListener(this);
    b2.addActionListener(this);
    b3.addActionListener(this);
    b4.addActionListener(this);
    setSize(400,400);
    setVisible(true);
  }
  public void actionPerformed(ActionEvent ae){
    if(ae.getSource()==b1)
    new Insert();
    else if(ae.getSource()==b2)
    new Update();
    else if(ae.getSource()==b3)
    new Display();
    else if(ae.getSource()==b4)
    new Promote();
  }
  public static void main(String[] args){
    new DbCon();
  }
}
