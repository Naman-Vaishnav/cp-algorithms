//package SCC;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class FlightRoutesCheck{

	public static void dfs(int cur,List<List<Integer>> adj,boolean[] vis,List<Integer> topo){
		vis[cur]=true;
		for(int child:adj.get(cur)){
			if(vis[child]==false)dfs(child,adj,vis,topo);
		}
		topo.add(cur);
	}  
 
	public static void main(String[] args) throws IOException{
		Reader fr=new Reader();
		int n,m;
		n=fr.nextInt();
		m=fr.nextInt();

		List<List<Integer>> adj=new ArrayList<>();
		List<List<Integer>> adj_rev=new ArrayList<>();

		for(int i=0;i<=n;i++){
			adj.add(new ArrayList<>());
			adj_rev.add(new ArrayList<>());
		}
		for(int j=0;j<m;j++){
			int x=fr.nextInt(),y=fr.nextInt();
			adj.get(x).add(y);
			adj_rev.get(y).add(x);
		}

		//dfs topo
		List<Integer> topo=new ArrayList<>();
		boolean[] vis=new boolean[n+1];
		for(int i=1;i<=n;i++){
			if(vis[i]==false)dfs(i,adj,vis,topo);
		}
		//System.out.println(topo);
		Collections.reverse(topo);
		//System.out.println(topo);

		//dfs on rev
		Arrays.fill(vis,false);
		List<Integer> roots=new ArrayList<>();
		int[] rt=new int[n+1];
		int numOfSCCs=0;
		for(int cur:topo){
			if(vis[cur]==true)continue;
			numOfSCCs++;
			List<Integer> curComponent=new ArrayList<>();
			dfs(cur,adj_rev,vis,curComponent);
			int curRoot=curComponent.get(0);
			roots.add(curRoot);
			for(int x:curComponent){
				rt[x]=curRoot;
			}

		}

		if(numOfSCCs==1)System.out.println("YES");
		else{
			System.out.println("NO");
			System.out.println(roots.get(1)+" "+roots.get(0));
		}

		//make SCC graph
		List<List<Integer>> adj_scc=new ArrayList<>();
		for(int i=1;i<=n;i++)adj_scc.add(new ArrayList<>());
		for(int i=1;i<=n;i++){
			for(int j:adj.get(i)){
				if(rt[i]!=rt[j]){
					adj_scc.get(rt[i]).add(rt[j]);
				}
			}
		}

	}


	







static class Reader { final private int BUFFER_SIZE = 1 << 16; private DataInputStream din; private byte[] buffer; private int bufferPointer, bytesRead; public Reader() { din = new DataInputStream(System.in); buffer = new byte[BUFFER_SIZE]; bufferPointer = bytesRead = 0; } public Reader(String file_name) throws IOException { din = new DataInputStream( new FileInputStream(file_name)); buffer = new byte[BUFFER_SIZE]; bufferPointer = bytesRead = 0; } public String readLine() throws IOException { byte[] buf = new byte[64]; int cnt = 0, c; while ((c = read()) != -1) { if (c == '\n') { if (cnt != 0) { break; } else { continue; } } buf[cnt++] = (byte)c; } return new String(buf, 0, cnt); } public int nextInt() throws IOException { int ret = 0; byte c = read(); while (c <= ' ') { c = read(); } boolean neg = (c == '-'); if (neg) c = read(); do { ret = ret * 10 + c - '0'; } while ((c = read()) >= '0' && c <= '9'); if (neg) return -ret; return ret; } public long nextLong() throws IOException { long ret = 0; byte c = read(); while (c <= ' ') c = read(); boolean neg = (c == '-'); if (neg) c = read(); do { ret = ret * 10 + c - '0'; } while ((c = read()) >= '0' && c <= '9'); if (neg) return -ret; return ret; } public double nextDouble() throws IOException { double ret = 0, div = 1; byte c = read(); while (c <= ' ') c = read(); boolean neg = (c == '-'); if (neg) c = read(); do { ret = ret * 10 + c - '0'; } while ((c = read()) >= '0' && c <= '9'); if (c == '.') { while ((c = read()) >= '0' && c <= '9') { ret += (c - '0') / (div *= 10); } } if (neg) return -ret; return ret; } private void fillBuffer() throws IOException { bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE); if (bytesRead == -1) buffer[0] = -1; } private byte read() throws IOException { if (bufferPointer == bytesRead) fillBuffer(); return buffer[bufferPointer++]; } public void close() throws IOException { if (din == null) return; din.close(); } }



}

