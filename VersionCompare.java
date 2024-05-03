class VersionCompare {
    public int compareVersion(String version1, String version2) {
        int i = 0;
        int j = 0;
        int n1 = version1.length();
        int n2 = version2.length();

        while (i < n1 || j < n2) {
            //Creating two strings to get current revision number
            StringBuilder s1 = new StringBuilder();
            StringBuilder s2 = new StringBuilder();
            //build string1 as long as i is less than n1 and we are not encountering a "." , which indicates we finished a revision
            while (i < n1 && version1.charAt(i) != '.' ) {
                s1.append(version1.charAt(i++));
            }
            //do the above procedure for version2 to build string2
            while (j < n2 && version2.charAt(j) != '.') {
                s2.append(version2.charAt(j++));
            }
            //parse them if we have revision number or make it 0 if we have no revision number
            int r1 = s1.length() == 0 ? 0 : Integer.parseInt(s1.toString());
            int r2 = s2.length() == 0 ? 0 : Integer.parseInt(s2.toString());

            if (r1 < r2) {
                return -1;
            } else if (r1 > r2) {
                return 1;
            }

            i++;
            j++;
        }
        //if no differences found at all revisions return 0
        return 0;
    }
}
