import java.util.*;

interface Autovehicul
{
    public double calculeazaGreutateaTotala();
    public String toString();
}

class Autoturism implements Autovehicul
{
    private String numar;
    private double greutate;
    private double grutatePasageri;

    public Autoturism(String numar, double greutate,double grutatePasageri)
    {
        this.greutate=greutate;
        this.numar=numar;
        this.grutatePasageri=grutatePasageri;
    }

    public double calculeazaGreutateaTotala()
    {
        return this.greutate+this.grutatePasageri;
    }

    public String toString()
    {
        return "Numar inmatriculare: "+numar+", greutate: "+greutate+", greutate pasageri: "+grutatePasageri;

    }

}

class Camion implements Autovehicul
{
    private String numar;
    private double greutate;
    private ArrayList<Autoturism> autoturisme=new ArrayList<Autoturism>();

    public Camion(String numar, double greutate)
    {
        this.numar=numar;
        this.greutate=greutate;

    }

    public void adaugaAutoturism(Autoturism a)
    {
        autoturisme.add(a);
    }

    public double calculeazaGreutateaTotala()
    {
        double sum=0;
        for(Autoturism a:autoturisme)
        {
            sum=sum+a.calculeazaGreutateaTotala();
        }
        return this.greutate +sum;
    }

    public String toString()
    {
        String st="Numar: "+numar+", Greutate: "+greutate;
        for(Autoturism a:autoturisme)
        {
            st=st+", "+a.toString();
        }
        return st;
    }
}

class Bac
{
    private double maxim;
    private double capacitate;
    private ArrayList<Autovehicul> autovehicule=new ArrayList<Autovehicul>();
    
    public Bac(double maxim)
    {
        this.maxim=maxim;
    }

    public boolean adaugaAutovehicul(Autovehicul a)
    {
        
        if(capacitate+a.calculeazaGreutateaTotala()<=maxim)
        {
            capacitate=capacitate+a.calculeazaGreutateaTotala();
            autovehicule.add(a);
            return true;
        }
        return false;
        
    }

    public String toString()
    {
        String st="G max: "+maxim;
        for(Autovehicul a:autovehicule)
        {
            st=st+", "+a.toString();

        }
        return st;
    }

}

class Main
{
    public static void main(String args[])
    {
        Autoturism a1= new Autoturism("1234",4,(double)0.3);
        Autoturism a2=new Autoturism("4563",5,(double)0.25);
        Autoturism a3=new Autoturism("6262",(double)4.5,(double)0.15);
        Camion c1= new Camion("3511",10);
        c1.adaugaAutoturism(a1);
        c1.adaugaAutoturism(a2);
        Bac b=new Bac(20);
        b.adaugaAutovehicul(c1);
        b.adaugaAutovehicul(a3);
        System.out.println(b.toString());

    }
}
