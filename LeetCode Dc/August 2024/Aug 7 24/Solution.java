class Solution {
    String ones[] = new String[]{"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    String tens[] = new String[]{"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    String teens[] = new String[]{"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    String base[] = new String[]{"", "Thousand", "Million", "Billion"};
    public String numberToWords(int num) {
        int count = 1;
        String res = "";
        while(num>0){
            System.out.println(num%1000);
            String temp = helper(num%1000);
            if(temp!=""){
                temp+=base[count-1]+" ";
            }
            res = temp+res;
            num/=1000; count++;
        }
        if(res.equals("")){
            res = "Zero";
        }
        return res.trim();
    }
    public String helper(int num){
        String res = "";
        int count = 1,prev = -1;
        while(num>0){
            String curr = "";
            int digit = num%10;
            if(count==1){
                prev = digit;
                if(num/10==0){
                    curr = ones[digit]+" ";
                }
            }
            else if(count==2){
                if(digit==0&&prev!=0){
                    curr= ones[prev]+" ";
                }
                else if(digit==1){
                    curr = teens[prev]+" ";
                }
                else if(digit!=0){
                    curr = tens[digit]+" ";
                    if(prev!=0){
                        curr+=ones[prev]+" ";
                    }
                }
            }
            else{
                if(digit!=0){
                    curr = ones[digit]+" Hundred ";
                }
            }
            res = curr+res;
            num/=10; count++;
        }
        return res;
    }
}
