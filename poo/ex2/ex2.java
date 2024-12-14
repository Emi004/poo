import java.util.*;


interface Task
{
    public boolean execute(double secunde);
    public String toString();
}


class SimpleTask implements Task
{
    private String name;
    private double timeout;
    
    public SimpleTask(String name, double timeout) {
        this.name = name;
        this.timeout = timeout;
    }

    public boolean execute(double secunde)
    {
        if(timeout<=0) return true;
        this.timeout=this.timeout-secunde;
        return false;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Name: "+name+",Time: "+timeout;
    }

}
class ComposedTask implements Task
{
    private String name;
    private ArrayList<SimpleTask> tasks=new ArrayList<SimpleTask>();
    
    public ComposedTask(String name, ArrayList<SimpleTask> tasks) {
        this.name = name;
        this.tasks = tasks;
    }

    public boolean execute(double secunde)
    {
        boolean b=true;
        for(SimpleTask task:tasks)
        {
            if(task.execute(secunde/tasks.size())==false)
            {
                b=false;
            }
        }
        if(b==true) return true;
        return false;
    }

    @Override
    public String toString() 
    {
        String st="Name: "+name+", Content:";
        for(SimpleTask task:tasks)
        {
            st=st+" "+task.toString();

        }
        return st;
    }

}
class Procesor
{
    private ArrayList<Task> tasks=new ArrayList<Task>();

    public Procesor(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    
    public void finishAllTasks()
    {
        for(Task t:tasks)
        {
            while(t.execute(5)==false);
        }
    }

    public String toString()
    {
        String st="Procesor: ";
        for (Task task : tasks) {
            st=st+task.toString()+";";
        }
        return st;
    }

}
class Main
{

    public static void main(String[] args) {
        SimpleTask s1=new SimpleTask("caca", 5);
        SimpleTask s2= new SimpleTask("pipi", 10);
        ComposedTask c1=new ComposedTask("toaleta", new ArrayList<SimpleTask>(Arrays.asList(s1,s2)));
        Procesor p=new Procesor(new ArrayList<>(Arrays.asList(c1)));
        System.out.println(p.toString());
        p.finishAllTasks();
        System.out.println(p.toString());
    }
}