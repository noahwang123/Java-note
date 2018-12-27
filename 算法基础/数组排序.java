public class traditionalOrder {
    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        //eg.
        List<Integer> example = Arrays.asList(1,2,3,4,9,6,8);

        Integer elementI;
        Integer elementJ;
        Integer i;
        Integer j;
        Integer size = example.size();
        for (i=0; i<size; i++) {
            for (j=0; j<size; j++) {
                if (example.get(j) > example.get(i)) {
                    //交换两个元素的位置
                    elementI = example.get(i);
                    elementJ = example.get(j);
                    example.set(i, elementJ);
                    example.set(j, elementI);
                }
            }
        }
    }
//计算次数 size^2

    public void combinedMerge(){
        //测试还未通过
        void mergeSort(List<Integer> S) {
            int n = S.size();
            if (n > 1) {
                final int h = n / 2;
                final int m = n - h;
                List<Integer> U = S.subList(0, h);
                List<Integer> V = S.subList(h, n);
                mergeSort(U);
                mergeSort(V);
                merge(h, m, U, V, S);
            }
        }

        void merge(int h, int m, final List<Integer> U, final List<Integer> V, List<Integer> S) {
            int i = 0;
            int j = 0;
            int k = 0;
            //int h = U.size();
            //int m = V.size();
            while (i <= h - 1 && j <= m - 1) {
                if (U.get(i) < V.get(j)) {
                    S.set(k, U.get(i));
                    i++;
                } else {
                    S.set(k, V.get(j));
                    j++;
                }
                k++;
            }
            if (i > h-1) {
                int x = h + j;
                while (x < h + m) {
                    S.set(k, V.get(x-h));
                    x++;
                }
            } else {
                int x = m + i;
                while (x < h + m) {
                    S.set(k, U.get(x-m));
                    x++;
                }
            }
        }
    }
}
