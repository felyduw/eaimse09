namespace EAI.A4.IntegrationWrapper_CameraSummaryXML
{
	partial class FormMainIWCameraSummaryXml
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
			this.labelCorrelationId1 = new System.Windows.Forms.Label();
			this.labelLastMsg1 = new System.Windows.Forms.Label();
			this.labelNrProcessedMsgs1 = new System.Windows.Forms.Label();
			this.label3 = new System.Windows.Forms.Label();
			this.label5 = new System.Windows.Forms.Label();
			this.label6 = new System.Windows.Forms.Label();
			tableLayoutPanel1 = new System.Windows.Forms.TableLayoutPanel();
			tableLayoutPanel1.SuspendLayout();
			this.SuspendLayout();
			// 
			// tableLayoutPanel1
			// 
			tableLayoutPanel1.ColumnCount = 2;
			tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 35F));
			tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 65F));
			tableLayoutPanel1.Controls.Add(this.labelCorrelationId1, 1, 2);
			tableLayoutPanel1.Controls.Add(this.labelLastMsg1, 1, 1);
			tableLayoutPanel1.Controls.Add(this.labelNrProcessedMsgs1, 1, 0);
			tableLayoutPanel1.Controls.Add(this.label3, 0, 2);
			tableLayoutPanel1.Controls.Add(this.label5, 0, 0);
			tableLayoutPanel1.Controls.Add(this.label6, 0, 1);
			tableLayoutPanel1.Location = new System.Drawing.Point(11, 12);
			tableLayoutPanel1.Name = "tableLayoutPanel1";
			tableLayoutPanel1.RowCount = 3;
			tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 33F));
			tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 33F));
			tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 34F));
			tableLayoutPanel1.Size = new System.Drawing.Size(379, 88);
			tableLayoutPanel1.TabIndex = 12;
			// 
			// labelCorrelationId1
			// 
			this.labelCorrelationId1.Anchor = System.Windows.Forms.AnchorStyles.Left;
			this.labelCorrelationId1.AutoSize = true;
			this.labelCorrelationId1.Location = new System.Drawing.Point(135, 66);
			this.labelCorrelationId1.Name = "labelCorrelationId1";
			this.labelCorrelationId1.Size = new System.Drawing.Size(146, 13);
			this.labelCorrelationId1.TabIndex = 8;
			this.labelCorrelationId1.Text = "waiting for the first message...";
			// 
			// labelLastMsg1
			// 
			this.labelLastMsg1.Anchor = System.Windows.Forms.AnchorStyles.Left;
			this.labelLastMsg1.AutoSize = true;
			this.labelLastMsg1.Location = new System.Drawing.Point(135, 37);
			this.labelLastMsg1.Name = "labelLastMsg1";
			this.labelLastMsg1.Size = new System.Drawing.Size(146, 13);
			this.labelLastMsg1.TabIndex = 11;
			this.labelLastMsg1.Text = "waiting for the first message...";
			// 
			// labelNrProcessedMsgs1
			// 
			this.labelNrProcessedMsgs1.Anchor = System.Windows.Forms.AnchorStyles.Left;
			this.labelNrProcessedMsgs1.AutoSize = true;
			this.labelNrProcessedMsgs1.Location = new System.Drawing.Point(135, 8);
			this.labelNrProcessedMsgs1.Name = "labelNrProcessedMsgs1";
			this.labelNrProcessedMsgs1.Size = new System.Drawing.Size(13, 13);
			this.labelNrProcessedMsgs1.TabIndex = 9;
			this.labelNrProcessedMsgs1.Text = "0";
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
			// label5
			// 
			this.label5.Anchor = System.Windows.Forms.AnchorStyles.Left;
			this.label5.AutoSize = true;
			this.label5.Location = new System.Drawing.Point(3, 8);
			this.label5.Name = "label5";
			this.label5.Size = new System.Drawing.Size(119, 13);
			this.label5.TabIndex = 0;
			this.label5.Text = "# processed messages:";
			// 
			// label6
			// 
			this.label6.Anchor = System.Windows.Forms.AnchorStyles.Left;
			this.label6.AutoSize = true;
			this.label6.Location = new System.Drawing.Point(3, 37);
			this.label6.Name = "label6";
			this.label6.Size = new System.Drawing.Size(123, 13);
			this.label6.TabIndex = 2;
			this.label6.Text = "last message processed:";
			// 
			// FormMainIWCameraSummaryXml
			// 
			this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
			this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
			this.ClientSize = new System.Drawing.Size(402, 111);
			this.Controls.Add(tableLayoutPanel1);
			this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedToolWindow;
			this.Location = new System.Drawing.Point(500, 310);
			this.Name = "FormMainIWCameraSummaryXml";
			this.StartPosition = System.Windows.Forms.FormStartPosition.Manual;
			this.Text = "Integration Wrapper - Camera Summary XML";
			tableLayoutPanel1.ResumeLayout(false);
			tableLayoutPanel1.PerformLayout();
			this.ResumeLayout(false);

		}

		#endregion

		private System.Windows.Forms.Label labelLastMsg1;
		private System.Windows.Forms.Label labelNrProcessedMsgs1;
		private System.Windows.Forms.Label labelCorrelationId1;
		private System.Windows.Forms.Label label3;
		private System.Windows.Forms.Label label5;
		private System.Windows.Forms.Label label6;
	}
}

