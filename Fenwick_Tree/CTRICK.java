//https://www.spoj.com/problems/CTRICK/

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

class FenwickTree{
    int[] BIT;
    int N;

    void init(int N){
        BIT=new int[N+1];
        this.N=N;
    }

    void update(int i,int delta){
        for(;i<=N;i+=(i&-i)){
            BIT[i]+=delta;
        }
    }

    int sum(int i){
        int res=0;
        for(;i>0;i-=(i&-i)){
            res+=BIT[i];
        }
        return res;
    }
}

public class CTRICK {
    public static void main(String[] args) throws IOException {
        Reader fr=new Reader();
        int q=fr.nextInt();
        while(q-->0){
            FenwickTree bit=new FenwickTree();
            int n=fr.nextInt();
            int[] ans=new int[n+1];
           
            bit.init(n);
            for(int i=1;i<=n;i++){
                bit.update(i, 1);
            }

            int offSet=1,spacesRemaining=n;

            for(int curCard=1;curCard<=n;curCard++){
                offSet=(offSet+curCard)%spacesRemaining;
                if(offSet==0)offSet=spacesRemaining;
                spacesRemaining--;

                int l=0,r=n+1;

                while (r-l>1) {
                    int m=l+(r-l)/2;
                    if(bit.sum(m)<offSet)l=m;
                    else r=m;

                }
                //System.out.println("r="+r+"offSet="+offSet);
                //if(l==0)l++;
                ans[r]=curCard;bit.update(r, -1);

            }
            StringBuffer sb=new StringBuffer();
            sb.append("");
            for(int i=1;i<=n;i++)sb.append(ans[i]+" ");
            System.out.println(sb.toString());


        }
    }







        static class Reader { final private int BUFFER_SIZE = 1 << 16; private DataInputStream din; private byte[] buffer; private int bufferPointer, bytesRead; public Reader() { din = new DataInputStream(System.in); buffer = new byte[BUFFER_SIZE]; bufferPointer = bytesRead = 0; } public Reader(String file_name) throws IOException { din = new DataInputStream(new FileInputStream(file_name)); buffer = new byte[BUFFER_SIZE]; bufferPointer = bytesRead = 0; } public String readLine() throws IOException { byte[] buf = new byte[1001]; int cnt = 0, c; while ((c = read()) != -1) { if (c == '\n') break; buf[cnt++] = (byte) c; } return new String(buf, 0, cnt); } public int nextInt() throws IOException { int ret = 0; byte c = read(); while (c <= ' ') c = read(); boolean neg = (c == '-'); if (neg) c = read(); do { ret = ret * 10 + c - '0'; } while ((c = read()) >= '0' && c <= '9'); if (neg) return -ret; return ret; } public long nextLong() throws IOException { long ret = 0; byte c = read(); while (c <= ' ') c = read(); boolean neg = (c == '-'); if (neg) c = read(); do { ret = ret * 10 + c - '0'; } while ((c = read()) >= '0' && c <= '9'); if (neg) return -ret; return ret; } public double nextDouble() throws IOException { double ret = 0, div = 1; byte c = read(); while (c <= ' ') c = read(); boolean neg = (c == '-'); if (neg) c = read(); do { ret = ret * 10 + c - '0'; } while ((c = read()) >= '0' && c <= '9'); if (c == '.') { while ((c = read()) >= '0' && c <= '9') { ret += (c - '0') / (div *= 10); } } if (neg) return -ret; return ret; } private void fillBuffer() throws IOException { bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE); if (bytesRead == -1) buffer[0] = -1; } private byte read() throws IOException { if (bufferPointer == bytesRead) fillBuffer(); return buffer[bufferPointer++]; } public void close() throws IOException { if (din == null) return; din.close(); } }

}
