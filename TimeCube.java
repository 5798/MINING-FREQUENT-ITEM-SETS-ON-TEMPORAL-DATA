import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.*;  
import java.util.ArrayList;
/*To find Time cubes of respective patterns*/

public class TimeCube {
	static int ii,jj=0 ;
public static void main(String args[])
{

	try
	{
	Scanner s = new Scanner(System.in);
	int mnth,temp_mnth,fcount=1;
	System.out.println("Enter file name:");
	String fname = s.next();
	System.out.println("Enter Division hierarchy \n Enter 4 for Year Division \n Enter 3 for  Month Division \n Enter 2 for Day Division");
	int div=s.nextInt();
	DateDemo.temporize(fname);



	FileInputStream fstream = new FileInputStream("odateicopy.txt");//input file
	BufferedReader br = new BufferedReader(new InputStreamReader(fstream));



	String strLine;
	
	int i,count=0;
	//dividing into timecubes based on month
           while ((strLine = br.readLine()) != null  )   
          {
	File fout = new File("./temp/dd"+fcount+".txt");//output file
	FileOutputStream fos = new FileOutputStream(fout);
 
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
	//Read File Line By Line
	ArrayList<Integer> a2 = new ArrayList<Integer>();
            	jj++;
	     
            	for(String word:strLine.split(" "))
           	 {
	
	                 a2.add(Integer.parseInt(word));
            	}
	   
	   temp_mnth=a2.get(a2.size()-div);
	   
          	 for(i=0;i<a2.size();i++)
	       {
		  
          		  bw.write(a2.get(i) + " ");
		 
         	       }
			//System.out.println(a1);
	    bw.newLine();
	
	
	while ((strLine = br.readLine()) != null  )   
	{
	    jj++;
	    ArrayList<Integer> a1 = new ArrayList<Integer>();
            
           	 for(String word:strLine.split(" "))
            	{
	                 a1.add(Integer.parseInt(word));
          	  }
	  
	   mnth=a1.get(a1.size()-div);
		if(mnth==temp_mnth)
		{
	   		//System.out.println(count);
		
           		for(i=0;i<a1.size();i++)
	       		{
            			bw.write(a1.get(i) + " ");
			 
         		}
			//System.out.println(a1);
	    		bw.newLine();
		}
		else{
			break;
	          	}
	}
	bw.close();
	fcount++;
     
             }
	
	String[] a=new String[1];
	System.out.println("Enter support:");
	a[0] = s.next();
	System.out.println(jj);
	for(ii=1;ii<fcount;ii++)
	{	
		//checking with desity ..if satisfied finding temporal patters
		//if not continue
		BufferedReader reader = new BufferedReader(new FileReader("./temp/dd"+ii+".txt"));
		double lines = 0;
		while (reader.readLine() != null) lines++;
		reader.close();
		double density =(jj/fcount)*0.5;
		System.out.println("given denisty is "+density);
			if(lines<density)
		{
			System.out.println("\nInvalid Time cube "+ii);
			System.out.println();
			continue;
		}
		
		CopyCharacters.preprocess("dd"+ii+".txt");
		
		Apriori2 ap = new Apriori2(a); 
		ap.find_patterns(a);
		System.out.println("Temporal patterns for Time cube "+ii+":");
		print_temporal_patterns();
	}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
}

//temporal patterns function

public static void print_temporal_patterns() throws IOException {

	FileInputStream fstream = new FileInputStream("oapriori.txt");//input file
	BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

	ArrayList<ArrayList<Integer> > aList =  
                  		new ArrayList<ArrayList<Integer> >(); 


	String strLine;char ch='c';
	int i,count=0,m,n;

	//Read File Line By Line
	//retriving patterns from apriori
 	while ((strLine = br.readLine()) != null  )   
	{
            
            for(String word:strLine.split(" "))
            {  
	      ArrayList<Integer> a1 = new ArrayList<Integer>();          
			
	    	for(int j=1;j<(word.length()-1);j++)
 	    	{
	     	 
	     	 ch=word.charAt(j);
	     	 if(ch==',')
	       	  continue;
		  
	     	 a1.add(Character.getNumericValue(ch));
	    	}
		aList.add(a1);	
            }
	
	
	   
	}



	//Reading each timecube

	ArrayList<ArrayList<Integer> > aList3 =  
                  		new ArrayList<ArrayList<Integer> >(); 
	/*Reading Temporal data*/
       
 	FileInputStream fstream3 = new FileInputStream("./temp/dd"+ii+".txt");
	BufferedReader b2 = new BufferedReader(new InputStreamReader(fstream3));
	while ((strLine = b2.readLine()) != null  )   
	{
	    
	    ArrayList<Integer> a1 = new ArrayList<Integer>();
  		    for(String word:strLine.split(" "))
  		        {
	                a1.add(Integer.parseInt(word));
                }
	               
	        aList3.add(a1);
	        
	        
	}
	b2.close();





	
	int x=0;

	//Read File Line By Line 
	/*comparing frequent output with data to get where it is frequent*/
	ArrayList<ArrayList<Integer> > aList2 =  
                  new ArrayList<ArrayList<Integer> >(); 
	
       
 	FileInputStream fstream2 = new FileInputStream("ocopyiapriori.txt");
	BufferedReader b1 = new BufferedReader(new InputStreamReader(fstream2));
	
	while ((strLine = b1.readLine()) != null  )   
	{
	    
	    ArrayList<Integer> a1 = new ArrayList<Integer>();
  		    for(String word:strLine.split(" "))
  		        {
	                a1.add(Integer.parseInt(word));
                }
	               
	        aList2.add(a1);
	        
	        
	}
       b1.close();


  	

        /*checking patterns*/

	 System.out.println("[FREQUENTITEM] : START-END TIME INTERVAL[YEAR MONTH DAY TIME]"); 
	System.out.println();
	
	for(int k = 0,j2=0,k2=0,n2=0; k < aList.size(); k++) { 
 			int c=0;
				for(int j = 0; j < aList2.size(); j++) { 
 						
        						 m = aList.get(k).size(); 
        						 n = aList2.get(j).size();
      
        						if(isSubset( aList2.get(j),aList.get(k), n, m)) 
								{
            							  
								  c++;
								  
								 if(c==1)
								  {
								   
								   System.out.print(aList.get(k)+" : ");
								   for(i=n+3;i<(n+7);i++)
	       							   {
            							     System.out.print(aList3.get(j).get(i) + " ");
         							   }
									
								  }
									k2=k;n2=n;j2=j;
								
								}
								
								
			}
						
							
							if(c!=0) {System.out.print("- ");
        							for(i=n2+3;i<(n2+7);i++)
	       							   {
            							     System.out.print(aList3.get(j2).get(i) + " ");
         							   }
									System.out.println();
								}

								
			}							



}

//chcecking where pattern is there using copychars

static boolean isSubset(ArrayList<Integer> a1,  
                ArrayList<Integer> a2, int m, int n) 
    { 
        int i = 0; 
        int j = 0; 
        for (i = 0; i < n; i++) 
        { 
            for (j = 0; j < m; j++) 
                if(a2.get(i) == a1.get(j)) 
                    break; 
              
            /* If the above inner loop  
            was not broken at all then 
            arr2[i] is not present in 
            arr1[] */
            if (j == m) 
                return false; 
        } 
          
        /* If we reach here then all 
        elements of arr2[] are present 
        in arr1[] */
        return true; 
    } 
}