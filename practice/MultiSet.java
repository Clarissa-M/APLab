import java.util.*;

class SetElement implements Comparable<SetElement> {
	String key;
	int freq;
	SetElement(String key) {
		this.key = key;
		freq = 0;	
	}
	void update() { freq++; }
	public int compareTo(SetElement that)  {
		return -1*Integer.compare(freq, that.freq);
	}
	public String toString() { return String.format("<%s,%d>", key, freq); }
}
 
class MultiSet {
	Map<String, SetElement> map;
	MultiSet(String strSequence) {
		map = new HashMap<>();
		String[] tokens = strSequence.split("\\s+");
		for (String token: tokens) {
			SetElement x = map.get(token);
			if (x==null) {
				x = new SetElement(token);
				map.put(x.key, x); // push only once
			}
			x.update(); // increment freq 
		}
	}
	List<SetElement> top(int k) {
		List<SetElement> values = new ArrayList<>(map.values());
		Collections.sort(values);
		return values.subList(0, k);
	}	
	public static void main(String[] args) {
		MultiSet m1 = new MultiSet("a b a c a d b c a b d b b b f g h c a");
		MultiSet m2 = new MultiSet("a a a c a d d d a b d b b b f g h c f");
		System.out.println(m1.top(3));
		System.out.println(m2.top(3));
	}
}
