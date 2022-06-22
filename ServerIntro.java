import java.io.*;
import java.net.*;

public class ServerIntro
{
	public static void main(String[] args)
	{
		try{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the data-");
		String sendToSocket=br.readLine();
		ServerSocket serverSocket=new ServerSocket(10000);
		System.out.println("Server Socket object is constructed!");
		Socket socket=serverSocket.accept();
		System.out.println("Write the data on the socket-");
		OutputStream os=socket.getOutputStream();
		byte[] b=sendToSocket.getBytes();
		os.write(b);
		}catch(Exception ee)
		{
			ee.printStackTrace();
		}
	}
}

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the weightedUniformStrings function below.
    static String[] weightedUniformStrings(String s, int[] queries) {
        String[] result = new String[queries.length];
        for (int i = 0; i < result.length; ++i)
            result[i] = "";
        TreeMap map = new TreeMap();
        for (int i = 0; i < s.length(); ++i)
        {
            if (map.get(s.charAt(i)) == null)
                map.put(s.charAt(i), 1);
            else
                map.replace(s.charAt(i), (((Integer)map.get(s.charAt(i)))+1));
        }
        List list = new ArrayList<Integer>();
        Iterator <Map.Entry<String, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext())
        {
            Map.Entry<String, String> entry = iterator.next();
            char ch = (char)entry.getKey();
            int freq = Integer.parseInt(entry.getValue());
            int maxVal = freq * (((int)ch)-96);
            int currVal = ((int)ch)-96;
            while (currVal <= maxVal)
            {
                list.add(currVal);
                currVal += (((int)ch)-96);
            }
        }
        for (int i = 0; i < list.size(); ++i)
        {
            System.out.print(list.get(i)+" ");
        }
        System.out.println();
        return result;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = scanner.nextLine();

        int queriesCount = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] queries = new int[queriesCount];

        for (int i = 0; i < queriesCount; i++) {
            int queriesItem = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            queries[i] = queriesItem;
        }

        String[] result = weightedUniformStrings(s, queries);

        for (int i = 0; i < result.length; i++) {
            bufferedWriter.write(result[i]);

            if (i != result.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
