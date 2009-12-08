namespace EAI.A4.MyOrchestrator
{
	partial class FormMainMyOrchestrator
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
			System.Windows.Forms.TableLayoutPanel tableLayoutPanel1;
			System.Windows.Forms.TableLayoutPanel tableLayoutPanel2;
			System.Windows.Forms.TableLayoutPanel tableLayoutPanel3;
			this.labelCorrelationId1 = new System.Windows.Forms.Label();
			this.label3 = new System.Windows.Forms.Label();
			this.labelLastMsg1 = new System.Windows.Forms.Label();
			this.label1 = new System.Windows.Forms.Label();
			this.label2 = new System.Windows.Forms.Label();
			this.labelNrProcessedMsgs1 = new System.Windows.Forms.Label();
			this.labelCorrelationId2 = new System.Windows.Forms.Label();
			this.labelLastMsg2 = new System.Windows.Forms.Label();
			this.label7 = new System.Windows.Forms.Label();
			this.label11 = new System.Windows.Forms.Label();
			this.labelNrProcessedMsgs2 = new System.Windows.Forms.Label();
			this.label12 = new System.Windows.Forms.Label();
			this.label5 = new System.Windows.Forms.Label();
			this.labelCorrelationId3 = new System.Windows.Forms.Label();
			this.labelLastMsg3 = new System.Windows.Forms.Label();
			this.label6 = new System.Windows.Forms.Label();
			this.label9 = new System.Windows.Forms.Label();
			this.labelNrProcessedMsgs3 = new System.Windows.Forms.Label();
			this.label14 = new System.Windows.Forms.Label();
			this.label4 = new System.Windows.Forms.Label();
			this.labelLastMsg4 = new System.Windows.Forms.Label();
			this.labelCorrelationId4 = new System.Windows.Forms.Label();
			this.groupBox1 = new System.Windows.Forms.GroupBox();
			this.groupBox2 = new System.Windows.Forms.GroupBox();
			this.groupBox3 = new System.Windows.Forms.GroupBox();
			this.label8 = new System.Windows.Forms.Label();
			this.labelNrProcessedMsgs4 = new System.Windows.Forms.Label();
			tableLayoutPanel1 = new System.Windows.Forms.TableLayoutPanel();
			tableLayoutPanel2 = new System.Windows.Forms.TableLayoutPanel();
			tableLayoutPanel3 = new System.Windows.Forms.TableLayoutPanel();
			tableLayoutPanel1.SuspendLayout();
			tableLayoutPanel2.SuspendLayout();
			tableLayoutPanel3.SuspendLayout();
			this.groupBox1.SuspendLayout();
			this.groupBox2.SuspendLayout();
			this.groupBox3.SuspendLayout();
			this.SuspendLayout();
			// 
			// tableLayoutPanel1
			// 
			tableLayoutPanel1.ColumnCount = 2;
			tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 35F));
			tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 65F));
			tableLayoutPanel1.Controls.Add(this.labelCorrelationId1, 1, 2);
			tableLayoutPanel1.Controls.Add(this.label3, 0, 2);
			tableLayoutPanel1.Controls.Add(this.labelLastMsg1, 1, 1);
			tableLayoutPanel1.Controls.Add(this.label1, 0, 0);
			tableLayoutPanel1.Controls.Add(this.label2, 0, 1);
			tableLayoutPanel1.Controls.Add(this.labelNrProcessedMsgs1, 1, 0);
			tableLayoutPanel1.Location = new System.Drawing.Point(9, 19);
			tableLayoutPanel1.Name = "tableLayoutPanel1";
			tableLayoutPanel1.RowCount = 3;
			tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 33F));
			tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 33F));
			tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 34F));
			tableLayoutPanel1.Size = new System.Drawing.Size(379, 88);
			tableLayoutPanel1.TabIndex = 4;
			// 
			// labelCorrelationId1
			// 
			this.labelCorrelationId1.Anchor = System.Windows.Forms.AnchorStyles.Left;
			this.labelCorrelationId1.AutoSize = true;
			this.labelCorrelationId1.Location = new System.Drawing.Point(135, 66);
			this.labelCorrelationId1.Name = "labelCorrelationId1";
			this.labelCorrelationId1.Size = new System.Drawing.Size(146, 13);
			this.labelCorrelationId1.TabIndex = 5;
			this.labelCorrelationId1.Text = "waiting for the first message...";
			// 
			// label3
			// 
			this.label3.Anchor = System.Windows.Forms.AnchorStyles.Left;
			this.label3.AutoSize = true;
			this.label3.Location = new System.Drawing.Point(3, 66);
			this.label3.Name = "label3";
			this.label3.Size = new System.Drawing.Size(70, 13);
			this.label3.TabIndex = 4;
			this.label3.Text = "correlation id:";
			// 
			// labelLastMsg1
			// 
			this.labelLastMsg1.Anchor = System.Windows.Forms.AnchorStyles.Left;
			this.labelLastMsg1.AutoSize = true;
			this.labelLastMsg1.Location = new System.Drawing.Point(135, 37);
			this.labelLastMsg1.Name = "labelLastMsg1";
			this.labelLastMsg1.Size = new System.Drawing.Size(146, 13);
			this.labelLastMsg1.TabIndex = 3;
			this.labelLastMsg1.Text = "waiting for the first message...";
			// 
			// label1
			// 
			this.label1.Anchor = System.Windows.Forms.AnchorStyles.Left;
			this.label1.AutoSize = true;
			this.label1.Location = new System.Drawing.Point(3, 8);
			this.label1.Name = "label1";
			this.label1.Size = new System.Drawing.Size(111, 13);
			this.label1.TabIndex = 0;
			this.label1.Text = "# received messages:";
			// 
			// label2
			// 
			this.label2.Anchor = System.Windows.Forms.AnchorStyles.Left;
			this.label2.AutoSize = true;
			this.label2.Location = new System.Drawing.Point(3, 37);
			this.label2.Name = "label2";
			this.label2.Size = new System.Drawing.Size(115, 13);
			this.label2.TabIndex = 2;
			this.label2.Text = "last message received:";
			// 
			// labelNrProcessedMsgs1
			// 
			this.labelNrProcessedMsgs1.Anchor = System.Windows.Forms.AnchorStyles.Left;
			this.labelNrProcessedMsgs1.AutoSize = true;
			this.labelNrProcessedMsgs1.Location = new System.Drawing.Point(135, 8);
			this.labelNrProcessedMsgs1.Name = "labelNrProcessedMsgs1";
			this.labelNrProcessedMsgs1.Size = new System.Drawing.Size(13, 13);
			this.labelNrProcessedMsgs1.TabIndex = 1;
			this.labelNrProcessedMsgs1.Text = "0";
			// 
			// tableLayoutPanel2
			// 
			tableLayoutPanel2.ColumnCount = 2;
			tableLayoutPanel2.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 35F));
			tableLayoutPanel2.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 65F));
			tableLayoutPanel2.Controls.Add(this.labelCorrelationId2, 1, 2);
			tableLayoutPanel2.Controls.Add(this.labelLastMsg2, 1, 1);
			tableLayoutPanel2.Controls.Add(this.label7, 0, 2);
			tableLayoutPanel2.Controls.Add(this.label11, 0, 0);
			tableLayoutPanel2.Controls.Add(this.labelNrProcessedMsgs2, 1, 0);
			tableLayoutPanel2.Controls.Add(this.label12, 0, 1);
			tableLayoutPanel2.Location = new System.Drawing.Point(9, 19);
			tableLayoutPanel2.Name = "tableLayoutPanel2";
			tableLayoutPanel2.RowCount = 3;
			tableLayoutPanel2.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 33F));
			tableLayoutPanel2.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 33F));
			tableLayoutPanel2.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 34F));
			tableLayoutPanel2.Size = new System.Drawing.Size(379, 88);
			tableLayoutPanel2.TabIndex = 5;
			// 
			// labelCorrelationId2
			// 
			this.labelCorrelationId2.Anchor = System.Windows.Forms.AnchorStyles.Left;
			this.labelCorrelationId2.AutoSize = true;
			this.labelCorrelationId2.Location = new System.Drawing.Point(135, 66);
			this.labelCorrelationId2.Name = "labelCorrelationId2";
			this.labelCorrelationId2.Size = new System.Drawing.Size(146, 13);
			this.labelCorrelationId2.TabIndex = 8;
			this.labelCorrelationId2.Text = "waiting for the first message...";
			// 
			// labelLastMsg2
			// 
			this.labelLastMsg2.Anchor = System.Windows.Forms.AnchorStyles.Left;
			this.labelLastMsg2.AutoSize = true;
			this.labelLastMsg2.Location = new System.Drawing.Point(135, 37);
			this.labelLastMsg2.Name = "labelLastMsg2";
			this.labelLastMsg2.Size = new System.Drawing.Size(146, 13);
			this.labelLastMsg2.TabIndex = 7;
			this.labelLastMsg2.Text = "waiting for the first message...";
			// 
			// label7
			// 
			this.label7.Anchor = System.Windows.Forms.AnchorStyles.Left;
			this.label7.AutoSize = true;
			this.label7.Location = new System.Drawing.Point(3, 66);
			this.label7.Name = "label7";
			this.label7.Size = new System.Drawing.Size(70, 13);
			this.label7.TabIndex = 4;
			this.label7.Text = "correlation id:";
			// 
			// label11
			// 
			this.label11.Anchor = System.Windows.Forms.AnchorStyles.Left;
			this.label11.AutoSize = true;
			this.label11.Location = new System.Drawing.Point(3, 8);
			this.label11.Name = "label11";
			this.label11.Size = new System.Drawing.Size(111, 13);
			this.label11.TabIndex = 0;
			this.label11.Text = "# received messages:";
			// 
			// labelNrProcessedMsgs2
			// 
			this.labelNrProcessedMsgs2.Anchor = System.Windows.Forms.AnchorStyles.Left;
			this.labelNrProcessedMsgs2.AutoSize = true;
			this.labelNrProcessedMsgs2.Location = new System.Drawing.Point(135, 8);
			this.labelNrProcessedMsgs2.Name = "labelNrProcessedMsgs2";
			this.labelNrProcessedMsgs2.Size = new System.Drawing.Size(13, 13);
			this.labelNrProcessedMsgs2.TabIndex = 5;
			this.labelNrProcessedMsgs2.Text = "0";
			// 
			// label12
			// 
			this.label12.Anchor = System.Windows.Forms.AnchorStyles.Left;
			this.label12.AutoSize = true;
			this.label12.Location = new System.Drawing.Point(3, 37);
			this.label12.Name = "label12";
			this.label12.Size = new System.Drawing.Size(115, 13);
			this.label12.TabIndex = 2;
			this.label12.Text = "last message received:";
			// 
			// tableLayoutPanel3
			// 
			tableLayoutPanel3.ColumnCount = 2;
			tableLayoutPanel3.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 35F));
			tableLayoutPanel3.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 65F));
			tableLayoutPanel3.Controls.Add(this.labelNrProcessedMsgs4, 1, 3);
			tableLayoutPanel3.Controls.Add(this.labelCorrelationId3, 1, 2);
			tableLayoutPanel3.Controls.Add(this.labelLastMsg3, 1, 1);
			tableLayoutPanel3.Controls.Add(this.label6, 0, 2);
			tableLayoutPanel3.Controls.Add(this.label9, 0, 0);
			tableLayoutPanel3.Controls.Add(this.labelNrProcessedMsgs3, 1, 0);
			tableLayoutPanel3.Controls.Add(this.label14, 0, 1);
			tableLayoutPanel3.Controls.Add(this.label5, 0, 5);
			tableLayoutPanel3.Controls.Add(this.label4, 0, 4);
			tableLayoutPanel3.Controls.Add(this.labelCorrelationId4, 1, 5);
			tableLayoutPanel3.Controls.Add(this.labelLastMsg4, 1, 4);
			tableLayoutPanel3.Controls.Add(this.label8, 0, 3);
			tableLayoutPanel3.Location = new System.Drawing.Point(9, 19);
			tableLayoutPanel3.Name = "tableLayoutPanel3";
			tableLayoutPanel3.RowCount = 6;
			tableLayoutPanel3.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 16.66667F));
			tableLayoutPanel3.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 16.66667F));
			tableLayoutPanel3.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 16.66667F));
			tableLayoutPanel3.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 16.66667F));
			tableLayoutPanel3.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 16.66667F));
			tableLayoutPanel3.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 16.66667F));
			tableLayoutPanel3.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 20F));
			tableLayoutPanel3.Size = new System.Drawing.Size(379, 180);
			tableLayoutPanel3.TabIndex = 6;
			// 
			// label5
			// 
			this.label5.Anchor = System.Windows.Forms.AnchorStyles.Left;
			this.label5.AutoSize = true;
			this.label5.Location = new System.Drawing.Point(3, 158);
			this.label5.Name = "label5";
			this.label5.Size = new System.Drawing.Size(70, 13);
			this.label5.TabIndex = 11;
			this.label5.Text = "correlation id:";
			// 
			// labelCorrelationId3
			// 
			this.labelCorrelationId3.Anchor = System.Windows.Forms.AnchorStyles.Left;
			this.labelCorrelationId3.AutoSize = true;
			this.labelCorrelationId3.Location = new System.Drawing.Point(135, 68);
			this.labelCorrelationId3.Name = "labelCorrelationId3";
			this.labelCorrelationId3.Size = new System.Drawing.Size(146, 13);
			this.labelCorrelationId3.TabIndex = 9;
			this.labelCorrelationId3.Text = "waiting for the first message...";
			// 
			// labelLastMsg3
			// 
			this.labelLastMsg3.Anchor = System.Windows.Forms.AnchorStyles.Left;
			this.labelLastMsg3.AutoSize = true;
			this.labelLastMsg3.Location = new System.Drawing.Point(135, 38);
			this.labelLastMsg3.Name = "labelLastMsg3";
			this.labelLastMsg3.Size = new System.Drawing.Size(146, 13);
			this.labelLastMsg3.TabIndex = 7;
			this.labelLastMsg3.Text = "waiting for the first message...";
			// 
			// label6
			// 
			this.label6.Anchor = System.Windows.Forms.AnchorStyles.Left;
			this.label6.AutoSize = true;
			this.label6.Location = new System.Drawing.Point(3, 68);
			this.label6.Name = "label6";
			this.label6.Size = new System.Drawing.Size(70, 13);
			this.label6.TabIndex = 4;
			this.label6.Text = "correlation id:";
			// 
			// label9
			// 
			this.label9.Anchor = System.Windows.Forms.AnchorStyles.Left;
			this.label9.AutoSize = true;
			this.label9.Location = new System.Drawing.Point(3, 8);
			this.label9.Name = "label9";
			this.label9.Size = new System.Drawing.Size(111, 13);
			this.label9.TabIndex = 0;
			this.label9.Text = "# received messages:";
			// 
			// labelNrProcessedMsgs3
			// 
			this.labelNrProcessedMsgs3.Anchor = System.Windows.Forms.AnchorStyles.Left;
			this.labelNrProcessedMsgs3.AutoSize = true;
			this.labelNrProcessedMsgs3.Location = new System.Drawing.Point(135, 8);
			this.labelNrProcessedMsgs3.Name = "labelNrProcessedMsgs3";
			this.labelNrProcessedMsgs3.Size = new System.Drawing.Size(13, 13);
			this.labelNrProcessedMsgs3.TabIndex = 5;
			this.labelNrProcessedMsgs3.Text = "0";
			// 
			// label14
			// 
			this.label14.Anchor = System.Windows.Forms.AnchorStyles.Left;
			this.label14.AutoSize = true;
			this.label14.Location = new System.Drawing.Point(3, 38);
			this.label14.Name = "label14";
			this.label14.Size = new System.Drawing.Size(115, 13);
			this.label14.TabIndex = 2;
			this.label14.Text = "last message received:";
			// 
			// label4
			// 
			this.label4.Anchor = System.Windows.Forms.AnchorStyles.Left;
			this.label4.AutoSize = true;
			this.label4.Location = new System.Drawing.Point(3, 128);
			this.label4.Name = "label4";
			this.label4.Size = new System.Drawing.Size(115, 13);
			this.label4.TabIndex = 10;
			this.label4.Text = "last message received:";
			// 
			// labelLastMsg4
			// 
			this.labelLastMsg4.Anchor = System.Windows.Forms.AnchorStyles.Left;
			this.labelLastMsg4.AutoSize = true;
			this.labelLastMsg4.Location = new System.Drawing.Point(135, 128);
			this.labelLastMsg4.Name = "labelLastMsg4";
			this.labelLastMsg4.Size = new System.Drawing.Size(146, 13);
			this.labelLastMsg4.TabIndex = 12;
			this.labelLastMsg4.Text = "waiting for the first message...";
			// 
			// labelCorrelationId4
			// 
			this.labelCorrelationId4.Anchor = System.Windows.Forms.AnchorStyles.Left;
			this.labelCorrelationId4.AutoSize = true;
			this.labelCorrelationId4.Location = new System.Drawing.Point(135, 158);
			this.labelCorrelationId4.Name = "labelCorrelationId4";
			this.labelCorrelationId4.Size = new System.Drawing.Size(146, 13);
			this.labelCorrelationId4.TabIndex = 13;
			this.labelCorrelationId4.Text = "waiting for the first message...";
			// 
			// groupBox1
			// 
			this.groupBox1.Controls.Add(tableLayoutPanel1);
			this.groupBox1.Location = new System.Drawing.Point(12, 12);
			this.groupBox1.Name = "groupBox1";
			this.groupBox1.Size = new System.Drawing.Size(394, 113);
			this.groupBox1.TabIndex = 1;
			this.groupBox1.TabStop = false;
			this.groupBox1.Text = "UserInterface -> CameraSearchXML";
			// 
			// groupBox2
			// 
			this.groupBox2.Controls.Add(tableLayoutPanel2);
			this.groupBox2.Location = new System.Drawing.Point(12, 131);
			this.groupBox2.Name = "groupBox2";
			this.groupBox2.Size = new System.Drawing.Size(394, 113);
			this.groupBox2.TabIndex = 2;
			this.groupBox2.TabStop = false;
			this.groupBox2.Text = "CameraSearchXML -> CameraSummaryXML + CameraListBeautifier";
			// 
			// groupBox3
			// 
			this.groupBox3.Controls.Add(tableLayoutPanel3);
			this.groupBox3.Location = new System.Drawing.Point(12, 250);
			this.groupBox3.Name = "groupBox3";
			this.groupBox3.Size = new System.Drawing.Size(394, 207);
			this.groupBox3.TabIndex = 3;
			this.groupBox3.TabStop = false;
			this.groupBox3.Text = "CameraSummaryXML + CameraListBeautifier -> UserInterface";
			// 
			// label8
			// 
			this.label8.Anchor = System.Windows.Forms.AnchorStyles.Left;
			this.label8.AutoSize = true;
			this.label8.Location = new System.Drawing.Point(3, 98);
			this.label8.Name = "label8";
			this.label8.Size = new System.Drawing.Size(111, 13);
			this.label8.TabIndex = 14;
			this.label8.Text = "# received messages:";
			// 
			// labelNrProcessedMsgs4
			// 
			this.labelNrProcessedMsgs4.Anchor = System.Windows.Forms.AnchorStyles.Left;
			this.labelNrProcessedMsgs4.AutoSize = true;
			this.labelNrProcessedMsgs4.Location = new System.Drawing.Point(135, 98);
			this.labelNrProcessedMsgs4.Name = "labelNrProcessedMsgs4";
			this.labelNrProcessedMsgs4.Size = new System.Drawing.Size(13, 13);
			this.labelNrProcessedMsgs4.TabIndex = 15;
			this.labelNrProcessedMsgs4.Text = "0";
			// 
			// FormMainMyOrchestrator
			// 
			this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
			this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
			this.ClientSize = new System.Drawing.Size(418, 473);
			this.Controls.Add(this.groupBox3);
			this.Controls.Add(this.groupBox2);
			this.Controls.Add(this.groupBox1);
			this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedToolWindow;
			this.Location = new System.Drawing.Point(50, 150);
			this.Name = "FormMainMyOrchestrator";
			this.StartPosition = System.Windows.Forms.FormStartPosition.Manual;
			this.Text = "My Orchestrator";
			tableLayoutPanel1.ResumeLayout(false);
			tableLayoutPanel1.PerformLayout();
			tableLayoutPanel2.ResumeLayout(false);
			tableLayoutPanel2.PerformLayout();
			tableLayoutPanel3.ResumeLayout(false);
			tableLayoutPanel3.PerformLayout();
			this.groupBox1.ResumeLayout(false);
			this.groupBox2.ResumeLayout(false);
			this.groupBox3.ResumeLayout(false);
			this.ResumeLayout(false);

		}

		#endregion

		private System.Windows.Forms.GroupBox groupBox1;
		private System.Windows.Forms.GroupBox groupBox2;
		private System.Windows.Forms.GroupBox groupBox3;
		private System.Windows.Forms.Label label1;
		private System.Windows.Forms.Label labelLastMsg1;
		private System.Windows.Forms.Label label2;
		private System.Windows.Forms.Label labelNrProcessedMsgs1;
		private System.Windows.Forms.Label labelLastMsg2;
		private System.Windows.Forms.Label labelLastMsg3;
		private System.Windows.Forms.Label labelNrProcessedMsgs3;
		private System.Windows.Forms.Label label3;
		private System.Windows.Forms.Label labelCorrelationId1;
		private System.Windows.Forms.Label labelCorrelationId2;
		private System.Windows.Forms.Label label7;
		private System.Windows.Forms.Label label11;
		private System.Windows.Forms.Label labelNrProcessedMsgs2;
		private System.Windows.Forms.Label label12;
		private System.Windows.Forms.Label labelCorrelationId3;
		private System.Windows.Forms.Label label6;
		private System.Windows.Forms.Label label9;
		private System.Windows.Forms.Label label14;
		private System.Windows.Forms.Label label5;
		private System.Windows.Forms.Label label4;
		private System.Windows.Forms.Label labelLastMsg4;
		private System.Windows.Forms.Label labelCorrelationId4;
		private System.Windows.Forms.Label labelNrProcessedMsgs4;
		private System.Windows.Forms.Label label8;

	}
}

