package CLFLARR;
import java.util.*;
import java.io.IOException;
public class Main  {
    public static void main(String[] args) throws IOException {
        Scanner sc=new Scanner(System.in);
        int n,q;
        n=sc.nextInt();
        q=sc.nextInt();
        int[][] queries=new int[q][3];
        int[] color=new int[n+2];
        for(int i=0;i<q;i++){
            queries[i][0]=sc.nextInt();
            queries[i][1]=sc.nextInt();
            queries[i][2]=sc.nextInt();
        }
        
        DSU dsu=new DSU(); 
        dsu.make_set(n+1);
        for(int i=q-1;i>=0;i--){
            int l=queries[i][0];
            int r=queries[i][1];
            int c=queries[i][2];

            for(int cur=dsu.find_set(l);cur<=r;cur=dsu.find_set(cur)){
                color[cur]=c;
                dsu.union_set(cur,cur+1);
                //System.out.println(cur);
            }

        }
        StringBuilder sb=new StringBuilder();
        for(int i=1;i<=n;i++){
            sb.append(color[i]+"\n") ;
        }
        System.out.println(sb.toString());

    }
    
}

/**
     *  
     */
      class DSU {
        int[] parent;
        void make_set(int n){
            parent=new int[n+2];
            Arrays.fill(parent, 0);
            for(int i=0;i<=n;i++){
                parent[i]=i;
            }
        }

        int find_set(int v){
            if(parent[v]==v)return v;
            return parent[v]=find_set(parent[v]);
        }

        void union_set(int a,int b){
             a=find_set(a);
             b=find_set(b);
            int leader=Math.max(a,b);
            parent[a]=leader;
            parent[b]=leader;
        }
        
    }
