//package cp_algorithms.Fenwick_Tree;
//https://cses.fi/problemset/task/1739/
 
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;


  
 
public class ForestQueriesII {
    public static void main(String[] args) throws IOException {
        Reader fr=new Reader();
        int n=fr.nextInt();
        int q=fr.nextInt();
        int[][] arr=new int[n+1][n+1];
 
        for(int i=1;i<=n;i++){
                String ch=fr.readLine();
                for(int j=1;j<=n;j++){
                    if(ch.charAt(j-1)=='*')arr[i][j]=1;
                    else arr[i][j]=0;
                }    
        }
 
        FenwickTree bit=new FenwickTree();
        bit.build(n, n, arr);
        StringBuffer sb=new StringBuffer();
        sb.append("");
        while(q-->0){
            int type=fr.nextInt();
            int x=fr.nextInt();
            int y=fr.nextInt();
            if(type==1){
                if(bit.arr[x][y]==1){
                    bit.add(x, y, -1);
                    bit.arr[x][y]=0;
                }
                else {
                    bit.add(x, y, 1);
                    bit.arr[x][y]=1;
                }
            }
            else{
                int x2=fr.nextInt();
                int y2=fr.nextInt();
                int ans=bit.sumReg(x, y,x2,y2);
                sb.append(ans+"\n");
            }
        }  
        System.out.println(sb.toString());
 
    }
    static class Reader { final private int BUFFER_SIZE = 1 << 16; private DataInputStream din; private byte[] buffer; private int bufferPointer, bytesRead; public Reader() { din = new DataInputStream(System.in); buffer = new byte[BUFFER_SIZE]; bufferPointer = bytesRead = 0; } public Reader(String file_name) throws IOException { din = new DataInputStream(new FileInputStream(file_name)); buffer = new byte[BUFFER_SIZE]; bufferPointer = bytesRead = 0; } public String readLine() throws IOException { byte[] buf = new byte[1001]; int cnt = 0, c; while ((c = read()) != -1) { if (c == '\n') break; buf[cnt++] = (byte) c; } return new String(buf, 0, cnt); } public int nextInt() throws IOException { int ret = 0; byte c = read(); while (c <= ' ') c = read(); boolean neg = (c == '-'); if (neg) c = read(); do { ret = ret * 10 + c - '0'; } while ((c = read()) >= '0' && c <= '9'); if (neg) return -ret; return ret; } public long nextLong() throws IOException { long ret = 0; byte c = read(); while (c <= ' ') c = read(); boolean neg = (c == '-'); if (neg) c = read(); do { ret = ret * 10 + c - '0'; } while ((c = read()) >= '0' && c <= '9'); if (neg) return -ret; return ret; } public double nextDouble() throws IOException { double ret = 0, div = 1; byte c = read(); while (c <= ' ') c = read(); boolean neg = (c == '-'); if (neg) c = read(); do { ret = ret * 10 + c - '0'; } while ((c = read()) >= '0' && c <= '9'); if (c == '.') { while ((c = read()) >= '0' && c <= '9') { ret += (c - '0') / (div *= 10); } } if (neg) return -ret; return ret; } private void fillBuffer() throws IOException { bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE); if (bytesRead == -1) buffer[0] = -1; } private byte read() throws IOException { if (bufferPointer == bytesRead) fillBuffer(); return buffer[bufferPointer++]; } public void close() throws IOException { if (din == null) return; din.close(); } }

}
 
 
class FenwickTree {
    int[][] bit;
    int [][] arr;
    int n,m;
    void build(int n,int m,int [][] a){
        arr=a;
        this.n=n;this.m=m;
        bit=new int[n+1][m+1];
        for(int x=1;x<=n;x++){
            for(int y=1;y<=m;y++){
                add(x,y,arr[x][y]);
            }
        }
    }
 
    void add(int i,int j,int val){
        
        for(int x=i;x<=n;x+=(x&-x)){
            for(int y=j;y<=m;y+=(y&-y)){
                bit[x][y]+=val;
            }
        }
    }
 
    int sum(int i,int j){
        int res=0;
        for(int x=i;x>0;x-=(x&-x)){
            for(int y=j;y>0;y-=(y&-y)){
                res+=bit[x][y];
            }
        }
        return res;
    }
 
    int sumReg(int x1,int y1,int x2,int y2){
        return sum(x2, y2)-sum(x1-1, y2)-sum(x2, y1-1)+sum(x1-1, y1-1);
    }
 
    
}