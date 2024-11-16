package Strings.Rabin_Krap;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class NAJPF {

    static List<Integer> rabin_krap(String t,String p){
        List <Integer> occ=new ArrayList<>();
        int T=t.length(),P=p.length();
        int base=31,mod=(int)Math.pow(10, 9)+9;
        int[] p_pow=new int[Math.max(T, P)];
        p_pow[0]=1;
        for(int i=1;i<p_pow.length;i++){
            p_pow[i]=(p_pow[i-1]*base)%mod;
        }

        int hash_p=0;
        for(int i=0;i<p.length();i++){
            hash_p+=(p_pow[i]*(p.charAt(i)-'a'+1))%mod;
            hash_p%=mod;
        }

        int[] hash_t=new int[T+1];
        for(int i=0;i<T;i++){
            hash_t[i+1]=hash_t[i]+(t.charAt(i)-'a'+1)*p_pow[i];
            hash_t[i+1]%=mod;
        }

        for(int i=0;i+P-1<T;i++){
            int subStrT_hash=hash_t[i+P]-hash_t[i];
            if(subStrT_hash==hash_p*p_pow[i])occ.add(i);
        }

        return occ;
    }
    public static void main(String[] args) throws IOException {
        Reader fr=new Reader();
        int test=fr.nextInt();
        StringBuffer sb=new StringBuffer();
        while(test-->0){
        String line=fr.readLine();
        String t=line.split(" ")[0];
        String p=line.split(" ")[1];

        List<Integer> occurences=rabin_krap( t, p);

        
        sb.append("");
        if(occurences.size()==0)sb.append("Not Found");
        else{
            sb.append(occurences.size());
            sb.append("\n");
            for(int oc:occurences)sb.append(oc+1+" ");
        }
        sb.append("\n");
        sb.append("\n");
      }
      System.out.println(sb.toString());

    }





    static class Reader {
        final private int BUFFER_SIZE = 1 << 16; // 64 KB buffer size
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;
    
        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }
    
        public Reader(String file_name) throws IOException {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }
    
        public String readLine() throws IOException {
            StringBuilder sb = new StringBuilder();
            int c;
            while ((c = read()) != -1) {
                if (c == '\n') break;
                sb.append((char) c);
            }
            return sb.toString();
        }
    
        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ') c = read();
            boolean neg = (c == '-');
            if (neg) c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            return neg ? -ret : ret;
        }
    
        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, 0, BUFFER_SIZE);
            bufferPointer = 0;
            if (bytesRead == -1) {
                buffer[0] = -1; // End of stream marker
            }
        }
    
        private byte read() throws IOException {
            if (bufferPointer >= bytesRead) {
                fillBuffer();
                if (bytesRead <= 0) {
                    return -1; // End of stream
                }
            }
            return buffer[bufferPointer++];
        }
    
        public void close() throws IOException {
            if (din != null) din.close();
        }
    }
    
}
