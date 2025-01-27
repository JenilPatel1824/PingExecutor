import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

///  -- >> Single ip ping using ping
public class SingleCommand{


    public static void main(String[] args) {

        boolean reachable = false;
        System.out.println("Enter IP: ");
        Scanner sc = new Scanner(System.in);
        String ip = sc.nextLine();
        ProcessBuilder pb = new ProcessBuilder("ping", "-c", "3", ip);
        pb.redirectErrorStream(true);
        Pattern pattern = Pattern.compile("\\b0% packet loss");
        try {
            Process p = pb.start();
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                Matcher m = pattern.matcher(line);
                if (m.find())
                    reachable = true;
                //System.out.println(line);
            }
            br.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        System.out.println("host ip: " + ip + " is " + (reachable ? "reachable" : "not reachable"));

    }
}
