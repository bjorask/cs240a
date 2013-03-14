package importers;

public class Drawing {
	
	public int id = -1;
	public int gameId;
	public int number;
	public String word;
	public int player;
	public String[] lines;
	
	public int player1time;
	public int player2time;
	public int player3time;
	public int player4time;
	public int player5time;
	
	public String archiveDate;
	
	public boolean isValid = false;
	
	public Drawing(){
		
	}
	
	public void getDrawing(String strLine){
		
		String[] drawData = strLine.split(",");
		if(drawData.length == 12){
			id = Integer.parseInt(drawData[0]);
			gameId = Integer.parseInt(drawData[1]);
			number = Integer.parseInt(drawData[2]);
			
			word = drawData[3];
			player = Integer.parseInt(drawData[4]);
						
			lines = drawData[5].split("\\$");
			
			isValid = true;
		}else{
			isValid = false;
		}
	}
	public int countLines(){
		return lines.length;
	}
	
	public int getColor(int lineNumber){
		String[] lineData = lines[lineNumber].split("-");
		return (lineData.length == 4) ? Integer.parseInt(lineData[3]) : -1;
	}
	
	public float[] getColorWeights(){
		float[] colorWeights = new float[10];
		float length = 0;
		
		//Init array to 0
		for (int i = 0; i < colorWeights.length; i++) {
			colorWeights[i] = 0;
		}
		
		//Count color of lines
		for (int i = 0; i < lines.length; i++) {
			if(getColor(i) > 0){
				colorWeights[getColor(i)] += 1;
			}else{
				isValid = false;
				return colorWeights;
			}
		}
		
		for (int i = 0; i < colorWeights.length; i++) {
			length += colorWeights[i]*colorWeights[i];
		}
		
		//Calculate percentages
		for (int i = 0; i < colorWeights.length; i++) {
			colorWeights[i] = (float)(colorWeights[i]/Math.sqrt(length));
		}
		return colorWeights;
	}
}