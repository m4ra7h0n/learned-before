package jlu.entity;





public class FileMessage {//�ļ�ʵ��
	
		private int id;//�ļ�id
		private int userid;//�û�id
		private String fileName;//�ļ����
		private String filePath;//�ļ����·��
		private String date;//�ļ��ϴ�����
		private String name;//�ļ��ϴ�����
		private String kind;//�ļ��ϴ�����
		public String getKind() {
			return kind;
		}
		public void setKind(String kind) {
			this.kind = kind;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		//private Date date;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public int getUserId() {
			return userid;
		}
		public void setUserId(int id) {
			this.userid = id;
		}
		public String getFileName() {
			return fileName;
		}
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
		public String getFilePath() {
			return filePath;
		}
		public void setFilePath(String filePath) {
			this.filePath = filePath;
		}
		public void setDate(String date) {
			this.date = date;
		}
		public String getDate() {
			return date;
		}
		public FileMessage(){}
		public FileMessage(int id,int userid,String fileName,String filePath,String date){
			this.id=id;
			this.userid=userid;
			this.fileName=fileName;
			this.filePath=filePath;
			this.date=date;
		}
		public FileMessage(String fileName,String filePath,String date){
			this.fileName=fileName;
			this.filePath=filePath;
			this.date=date;
		}
	}


