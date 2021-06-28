class GenericPatternDemo {
	// in: <'*', 3> out: "***"
	static String repeatChar(char ch, int n) {
		StringBuilder buff = new StringBuilder();
		for (int i=0; i < n; i++)
			buff.append(ch);
		return buff.toString();
	}

	// in: <'(', 2, ')', 2> out: "((  ))"
	static String genLine(char left, int nleft, char right, int nright, int width) {
		int nblanks =  width - (nleft + nright);
		if (nblanks <= 0)
			return null;

		String left_str = repeatChar(left, nleft);
		String right_str = repeatChar(right, nright);
		String mid_blank = repeatChar(' ', nblanks);

		return left_str + mid_blank + right_str; 
	}

	public static void main(String[] args) {
		int i = 1;
		String line;

		if (args.length < 3) {
			System.err.println("usage java GenericPatternDemo <left_char> <right_char> <width>");
			return;
		}

		final int N = Integer.parseInt(args[2]);
		final char LEFT_PIXEL = args[0].charAt(0);
		final char RIGHT_PIXEL = args[1].charAt(0);
			
		while ((line =  genLine(LEFT_PIXEL, i, RIGHT_PIXEL, i, N)) != null) {
			System.out.println(line);
			i++;
		}
	}
}
