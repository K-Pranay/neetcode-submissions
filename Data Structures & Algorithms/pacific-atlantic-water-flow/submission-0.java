class Solution {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int r = heights.length;
        int c = heights[0].length;
        boolean[][] pacific = new boolean[r][c];
        boolean[][] atlantic = new boolean[r][c];
        boolean[][] visited = new boolean[r][c];

        Queue<int[]> queue = new ArrayDeque<>();

        // pacific
        for(int i = 0 ; i < r ; i++) {
            pacific[i][0] = true;
            visited[i][0] = true;
            queue.offer(new int[]{i,0});
        }
        for(int i = 1 ; i < c ; i++){
            pacific[0][i] = true;
            visited[0][i] = true;            
            queue.offer(new int[]{0,i});
        }
        while(!queue.isEmpty()){
            int[] curr = queue.poll();
            int i = curr[0];
            int j = curr[1];
            OfferDirectionPossible(i+1,j, heights, heights[i][j], queue, pacific, visited );
            OfferDirectionPossible(i-1,j, heights, heights[i][j], queue, pacific, visited );
            OfferDirectionPossible(i,j+1, heights, heights[i][j], queue, pacific, visited );
            OfferDirectionPossible(i,j-1, heights, heights[i][j], queue, pacific, visited );
        }
        boolean[][] visited2 = new boolean[r][c];

        // atlantic
        for(int i = 0 ; i < r ; i++) {
            atlantic[i][c-1] = true;
            visited[i][c-1] = true;
            queue.offer(new int[]{i, c-1});
        }
        for(int i = 0 ; i < c ; i++){
            atlantic[r-1][i] = true;
            visited[r-1][i] = true;            
            queue.offer(new int[]{r-1,i});
        }
        while(!queue.isEmpty()){
            int[] curr = queue.poll();
            int i = curr[0];
            int j = curr[1];
            OfferDirectionPossible(i+1,j, heights, heights[i][j], queue, atlantic, visited2 );
            OfferDirectionPossible(i-1,j, heights, heights[i][j], queue, atlantic, visited2 );
            OfferDirectionPossible(i,j+1, heights, heights[i][j], queue, atlantic, visited2 );
            OfferDirectionPossible(i,j-1, heights, heights[i][j], queue, atlantic, visited2 );
        }

        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0 ; i < r ; i++){
            for(int j = 0 ; j < c ; j++) {
                if(pacific[i][j] && atlantic[i][j]){
                    res.add(Arrays.asList(i, j));
                }
            }
        }
        return res;
    }

    private void OfferDirectionPossible(int i , int j , int[][] heights, int currValue, Queue<int[]> queue, boolean[][] ocean, boolean[][] visited){
        int r = heights.length;
        int c = heights[0].length;
        if(i<0 || j < 0 || i > r-1 || j > c-1) return;
        if( heights[i][j] >= currValue && !ocean[i][j] && !visited[i][j]){
            queue.add(new int[]{i,j});
            visited[i][j] = true;
            ocean[i][j] = true;
        }
            
    }


}
