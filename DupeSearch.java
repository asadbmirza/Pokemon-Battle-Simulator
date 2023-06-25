class DupeSearch {
  public static boolean search(int[] arr, int range) {
    int count=0;
    for (int i = 0;i<range;i++) {
      for (int j =0;j<range;j++) {
        if (arr[j] == arr[i]) {
          count++;
        }
        if (count == 2) {
          return true;
        }
      }
      count = 0;
    }
    return false;
  }
}