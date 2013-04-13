package intelligenceartificielle;
public class Traduction
    {
        public int code;
        public String mot;

        public Traduction(int code, String phrase)
        {
            this.code = code;
            this.mot = phrase;
        }
        
        public Traduction()
        {
            this.code = 00000;
            this.mot = "erreur";
        }
        
    	public String transformeString()
    	{
    		String texte = "";
    		if(!mot.isEmpty())
    		{
    			texte = Integer.toString(code);
    			texte = texte + mot;
    		}
    		
    		return texte;
    	}
    	
    	public void lireTraduction(String texte)
    	{

    			String[] parties = texte.split(" ");
    			code = Integer.parseInt(parties[0]);
                        mot = "";
                        for(int i = 1;i<parties.length;i++)
                        {
                            mot = mot + " " + parties[i];
                        }
    			return;
    	}

		@Override
		public String toString() {
			return "Traduction [code=" + code + ", mot=" + mot + "]";
		}

         public String getMot(){
             return mot;
         }
    }
