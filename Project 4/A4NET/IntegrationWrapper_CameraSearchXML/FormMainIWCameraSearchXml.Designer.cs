namespace IntegrationWrapper_CameraSearchXML
{
	partial class FormMainIWCameraSearchXml
	{
		/// <summary>
		/// Required designer variable.
		/// </summary>
		private System.ComponentModel.IContainer components = null;

		/// <summary>
		/// Clean up any resources being used.
		/// </summary>
		/// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
		protected override void Dispose(bool disposing)
		{
			if (disposing && (components != null))
			{
				components.Dispose();
			}
			base.Dispose(disposing);
		}

		#region Windows Form Designer generated code

		/// <summary>
		/// Required method for Designer support - do not modify
		/// the contents of this method with the code editor.
		/// </summary>
		private void InitializeComponent()
		{
			this.labelLastMsg1 = new System.Windows.Forms.Label();
			this.label2 = new System.Windows.Forms.Label();
			this.labelNrProcessedMsgs1 = new System.Windows.Forms.Label();
			this.label1 = new System.Windows.Forms.Label();
			this.SuspendLayout();
			// 
			// labelLastMsg1
			// 
			this.labelLastMsg1.AutoSize = true;
			this.labelLastMsg1.Location = new System.Drawing.Point(137, 51);
			this.labelLastMsg1.Name = "labelLastMsg1";
			this.labelLastMsg1.Size = new System.Drawing.Size(146, 13);
			this.labelLastMsg1.TabIndex = 7;
			this.labelLastMsg1.Text = "waiting for the first message...";
			// 
			// label2
			// 
			this.label2.AutoSize = true;
			this.label2.Location = new System.Drawing.Point(12, 51);
			this.label2.Name = "label2";
			this.label2.Size = new System.Drawing.Size(123, 13);
			this.label2.TabIndex = 6;
			this.label2.Text = "last message processed:";
			// 
			// labelNrProcessedMsgs1
			// 
			this.labelNrProcessedMsgs1.AutoSize = true;
			this.labelNrProcessedMsgs1.Location = new System.Drawing.Point(137, 21);
			this.labelNrProcessedMsgs1.Name = "labelNrProcessedMsgs1";
			this.labelNrProcessedMsgs1.Size = new System.Drawing.Size(13, 13);
			this.labelNrProcessedMsgs1.TabIndex = 5;
			this.labelNrProcessedMsgs1.Text = "0";
			// 
			// label1
			// 
			this.label1.AutoSize = true;
			this.label1.Location = new System.Drawing.Point(12, 21);
			this.label1.Name = "label1";
			this.label1.Size = new System.Drawing.Size(119, 13);
			this.label1.TabIndex = 4;
			this.label1.Text = "# processed messages:";
			// 
			// FormMainIWCameraSearchXml
			// 
			this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
			this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
			this.ClientSize = new System.Drawing.Size(394, 76);
			this.Controls.Add(this.labelLastMsg1);
			this.Controls.Add(this.label2);
			this.Controls.Add(this.labelNrProcessedMsgs1);
			this.Controls.Add(this.label1);
			this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedToolWindow;
			this.Location = new System.Drawing.Point(500, 180);
			this.Name = "FormMainIWCameraSearchXml";
			this.StartPosition = System.Windows.Forms.FormStartPosition.Manual;
			this.Text = "Integration Wrapper - Camera Search XML";
			this.ResumeLayout(false);
			this.PerformLayout();

		}

		#endregion

		private System.Windows.Forms.Label labelLastMsg1;
		private System.Windows.Forms.Label label2;
		private System.Windows.Forms.Label labelNrProcessedMsgs1;
		private System.Windows.Forms.Label label1;
	}
}

