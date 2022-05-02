
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;  

public class App {

    static int mtnNo = 0;
    static int airtelNo = 0;
    static int gloNo = 0;
    static int etisalatNo = 0;
    static int mtelNo = 0;
    static int invalidNo = 0;
    
    //Arrays consisting of the prefixes associated with the different service providers
    static final String[] mtnArray1 = new String[]{"07025","07026"};
    static final String[] mtnArray = new String[]{"0703","0706","0803","0806","0810","0813","0814","0816","0903","0906","0913","0916","0704"};
    static final String[] airtelArray = new String[]{"0701","0708","0802","0808","0812","0901","0902","0904","0907","0912"};
    static final String[] gloArray = new String[]{"0705","0805","0807","0811","0815","0905","0915"};
    static final String[] etisalatArray = new String[]{"0809","0817","0818","0909","0908"};
    static final String[] mtelArray = new String[]{"0804"};

    //Arrays have been converted to Lists so that we can use the contains() function
    static List<String> mtnList = new ArrayList<>(Arrays.asList(mtnArray));
    static List<String> mtnList1 = new ArrayList<>(Arrays.asList(mtnArray1));
    static List<String> airtelList = new ArrayList<>(Arrays.asList(airtelArray));
    static List<String> gloList = new ArrayList<>(Arrays.asList(gloArray));
    static List<String> etisalatList = new ArrayList<>(Arrays.asList(etisalatArray));
    static List<String> mtelList = new ArrayList<>(Arrays.asList(mtelArray));

    public static void main(String[]args) throws IOException{
        try{
            //Setup: to connect and read from the webpage or file
            String host = "https://grnhse-ghr-prod-s101.s3.eu-central-1.amazonaws.com/generic_attachments/attachments/402/213/810/original/PhoneNumbers.txt?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAVQGOLGY3RZPEZZOZ%2F20220429%2Feu-central-1%2Fs3%2Faws4_request&X-Amz-Date=20220429T161547Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=031f69832d5d84214cb67350c27ad31fdd9986308e04684d27d0603c7753de34";
            URL url = new URL(host.trim());
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

            System.out.println("Generating Summary Report.......");
            // Reading the file line by line and checking the first 4 or 5 digits of each line to
            // determine the service provider.
            String line;
            //int no = 0;
            while ((line = br.readLine()) != null) {
                if(mtelList.contains(line.substring(0,4))) mtelNo++;
                else if(etisalatList.contains(line.substring(0,4))) etisalatNo++;
                else if(gloList.contains(line.substring(0,4))) gloNo++;
                else if(airtelList.contains(line.substring(0,4))) airtelNo++;
                else if(mtnList.contains(line.substring(0,4)))mtnNo++;
                else if(mtnList1.contains(line.substring(0,5)))mtnNo++;
                else invalidNo++;
                // no++;
                // System.out.println("hi " + no);
            }
            br.close();

            //Prints out summary report
            System.out.println("-----------Summary Report-----------");
            System.out.println("Service Provider\tNo. of Users");
            System.out.println("MTEL: \t\t\t" + mtelNo);
            System.out.println("Etisalat: \t\t" + etisalatNo);
            System.out.println("Glo: \t\t\t" + gloNo);
            System.out.println("Airtel: \t\t" + airtelNo);
            System.out.println("MTN: \t\t\t" + mtnNo);
            System.out.println("Invalid: \t\t" + invalidNo);
        }
        catch (MalformedURLException e) {
            System.out.println("Malformed URL: " + e.getMessage());
            System.out.println("The URL might be faulty");

        }
        catch (IOException e) {
            System.out.println("I/O Error: " + e.getMessage());
            System.out.println("Check your connection and retry");
        }
    }
}