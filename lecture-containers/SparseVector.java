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

public class SparseVector implements Comparable<SparseVector> {
    String word;
    Map<String, SetElement> map;
    float sim;

    SparseVector(String word) {
        this.word = word;
        map = new HashMap<>();
    }

    void update(String context) {
        if (context.equals(word)) return; // do nothing

        SetElement x = map.get(context);
        if (x==null) {
            x = new SetElement(context);
            map.put(x.key, x); // push only once
        }
        x.update(); // increment freq
    }

    List<SetElement> top(int k) {
        List<SetElement> values = new ArrayList<>(map.values());
        Collections.sort(values);
        return values.subList(0, k);
    }

    @Override
    public String toString() {
        return word + ": " + top(10).toString();
    }

    float norm() {
        float norm = 0;
        for (SetElement x: map.values()) {
            norm += x.freq*x.freq;
        }
        return (float)Math.sqrt(norm);
    }

    float sim(SparseVector that) {
        float sim = 0;
        for (String w: this.map.keySet()) {
            // check if w matches with the other sparsevec
            SetElement that_w = that.map.get(w);
            if (that_w == null)
                continue;
            sim += this.map.get(w).freq * that_w.freq;
        }
        return sim/(this.norm() * that.norm());
    }

    @Override
    public int compareTo(SparseVector that) {
        return -1* Float.compare(sim, that.sim); // descending
    }
}
