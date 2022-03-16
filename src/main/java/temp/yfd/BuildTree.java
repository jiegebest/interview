package temp.yfd;

import java.util.*;

/**
 *  1
 *  2 3 4
 *  5 6 7
 *  8
 *  [ (1, 2), (1, 3), (1, 4), (2, 5), (2, 6), (4, 7), (7, 8) ]
 */
public class BuildTree {

	public NTreeNode buildTree(int[][] matrix) {
		// 存储关系表
		Map<Integer, List<NTreeNode>> map = initMap(matrix);
		// 寻找头结点
		int rootVal = findRoot(matrix);
		NTreeNode root = new NTreeNode(rootVal);
		// 层序重建树
		build(map, root);
		return root;
	}

	private Map<Integer, List<NTreeNode>> initMap(int[][] matrix) {
		Map<Integer, List<NTreeNode>> map = new HashMap<>();
		for (int[] arr : matrix) {
			List<NTreeNode> temp = map.containsKey(arr[0]) ? map.get(arr[0]) : new ArrayList<>();
			temp.add(new NTreeNode(arr[1]));
			map.put(arr[0], temp);
		}
		return map;
	}

	public int findRoot(int[][] matrix) {
		Set<Integer> first = new HashSet<>();
		Set<Integer> second = new HashSet<>();
		for (int[] arr : matrix) {
			first.add(arr[0]);
			second.add(arr[1]);
		}
		for (Integer key : second) {
			first.remove(key);
		}
		int rootNum = 0;
		for (Integer key : first) {
			rootNum = key;
		}
		return rootNum;
	}

	public void build(Map<Integer, List<NTreeNode>> map, NTreeNode root) {
		Queue<NTreeNode> queue = new LinkedList<>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			NTreeNode node = queue.poll();
			if (map.get(node.val) != null) {
				List<NTreeNode> children = map.get(node.val);
				List<NTreeNode> temp = new ArrayList<>();
				for (NTreeNode child : children) {
					temp.add(child);
					queue.offer(child);
				}
				node.children = temp;
			}
		}
	}

}

class NTreeNode {
	public int val;
	public List<NTreeNode> children;

	public NTreeNode(int val) {
		this.val = val;
		children = new ArrayList<>();
	}
}