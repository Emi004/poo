import java.util.*;

abstract class Utilizator
{

    abstract public float calculeazaVenit(int minute);
    abstract public String toString();
}

class  Subscriber extends Utilizator
{
    private String name;
    private int subscriptionTier;

    public Subscriber(String name, int subscriptionTier) {
        this.name = name;
        this.subscriptionTier = subscriptionTier;
    }

    public float calculeazaVenit(int minute)
    {
        return minute*1.5f*subscriptionTier;

    }

    public String toString()
    {
        return name+" - subscriber - "+ subscriptionTier;

    }

}

class Creator extends Utilizator
{
    private String name;
    private ArrayList<Subscriber> subscribers=new ArrayList<Subscriber>();
   
    public Creator(String name) {
        this.name = name;
    }
    
    public void adaugaSubscriber(Subscriber s)
    {
        subscribers.add(s);
    }
    
  
    public float calculeazaVenit(int minute) {
        float sum=0;
        for(Subscriber s:subscribers)
        {
            sum=sum+s.calculeazaVenit(minute);
        }
        return sum;
    }

    @Override
    public String toString() {
        String st=name+ " - creator - \nSubscriberi:";
        for(Subscriber s:subscribers)
        {
            st=st+"\n"+s.toString();

        }
        return st;
    }


}

class Platforma 
    {
        private Utilizator[] utilizatori=new Utilizator[1000];
        private int cnt;
        public boolean adaugaUtilizator(Utilizator u)
        {
            if(cnt>=utilizatori.length-1)
            {
                return false;
            }else
            {
                utilizatori[cnt++]=u;
                return true;

            }

        }
        public Utilizator determinaVIP(int minute)
        {
            if (cnt>=1) {
                Utilizator max=utilizatori[0];
                for (int index = 1; index < cnt; index++) 
                {
                    if (max.calculeazaVenit(minute)<utilizatori[index].calculeazaVenit(minute)) {
                        max=utilizatori[index];
                    }    
                }
                return max;
            }
            else
            {
                return null;
            }
            

        }
        
    }

class Main
{

    public static void main(String[] args) {
        Platforma p= new Platforma();    
        Creator c1=new Creator("Emi");
        Creator c2 =new Creator("xQc");
        c1.adaugaSubscriber(new Subscriber("mami", 1));
        c1.adaugaSubscriber(new Subscriber("tati", 2));
        c1.adaugaSubscriber(new Subscriber("fratimiu", 1));
        c2.adaugaSubscriber(new Subscriber("jessie", 2));
        p.adaugaUtilizator(c1);
        p.adaugaUtilizator(c2);
        System.out.println(p.determinaVIP(30).toString());
    }
}
