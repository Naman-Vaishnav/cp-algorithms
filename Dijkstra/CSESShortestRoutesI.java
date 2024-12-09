//package Dijkstra;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class CSESShortestRoutesI {  // Fixed class name
    public static class Pair<T1, T2> {
        T1 x;
        T2 y;

        Pair(T1 x, T2 y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void dijkstra(int source, List<List<Pair<Integer, Long>>> adj, long[] d) {
        Comparator<Pair<Long, Integer>> comp = (i, j) -> {
            return Long.compare(i.x, j.x);
        };
        PriorityQueue<Pair<Long, Integer>> ts = new PriorityQueue<>(comp);
        Pair<Long, Integer> temp = new Pair<>(0L, source);
		d[source]=0;
        ts.add(temp);
        while (ts.size() > 0) {
            Pair<Long, Integer> cur = ts.peek();
            ts.poll();
            for (Pair<Integer, Long> child : adj.get(cur.y)) {
                if (d[child.x] > d[cur.y] +child.y ) {
				
                   // ts.remove(new Pair<>(d[child.x], child.x));
                    d[child.x] = d[cur.y] + child.y;
                    ts.add(new Pair<>(d[child.x], child.x));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Reader fr = new Reader();
        int n = fr.nextInt(), m = fr.nextInt();
        List<List<Pair<Integer, Long>>> adj = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 1; i <= m; i++) {
            int x = fr.nextInt(), y = fr.nextInt();Long len = fr.nextLong();
            adj.get(x).add(new Pair<>(y, len));
        }

        long[] d = new long[n + 1];
        Arrays.fill(d, Long.MAX_VALUE);
        dijkstra(1, adj, d);
        for (int i = 1; i <= n; i++) {
            System.out.print(d[i]+" ");
        }
    }

    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
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
            byte[] buf = new byte[64];
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n') {
                    if (cnt != 0) {
                        break;
                    } else {
                        continue;
                    }
                }
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            boolean neg = (c == '-');
            if (neg) c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (neg) return -ret;
            return ret;
        }

        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ') c = read();
            boolean neg = (c == '-');
            if (neg) c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (neg) return -ret;
            return ret;
        }

        public double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ') c = read();
            boolean neg = (c == '-');
            if (neg) c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }
            if (neg) return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1) buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead) fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            if (din == null) return;
            din.close();
        }
    }
}
