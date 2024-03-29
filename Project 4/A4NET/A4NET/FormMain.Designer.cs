﻿namespace EAI.A4.UserInterface
{
	partial class FormMain
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
			this.labelQuery = new System.Windows.Forms.Label();
			this.textBoxQuery = new System.Windows.Forms.TextBox();
			this.buttonGo = new System.Windows.Forms.Button();
			this.statusStripMain = new System.Windows.Forms.StatusStrip();
			this.toolStripStatusLabelResults = new System.Windows.Forms.ToolStripStatusLabel();
			this.backgroundWorkerInbox = new System.ComponentModel.BackgroundWorker();
			this.buttonClean = new System.Windows.Forms.Button();
			this.textBoxOutput = new System.Windows.Forms.TextBox();
			this.statusStripMain.SuspendLayout();
			this.SuspendLayout();
			// 
			// labelQuery
			// 
			this.labelQuery.AutoSize = true;
			this.labelQuery.Location = new System.Drawing.Point(12, 9);
			this.labelQuery.Name = "labelQuery";
			this.labelQuery.Size = new System.Drawing.Size(38, 13);
			this.labelQuery.TabIndex = 0;
			this.labelQuery.Text = "Query:";
			// 
			// textBoxQuery
			// 
			this.textBoxQuery.Location = new System.Drawing.Point(56, 6);
			this.textBoxQuery.Name = "textBoxQuery";
			this.textBoxQuery.Size = new System.Drawing.Size(599, 20);
			this.textBoxQuery.TabIndex = 1;
			// 
			// buttonGo
			// 
			this.buttonGo.Location = new System.Drawing.Point(661, 4);
			this.buttonGo.Name = "buttonGo";
			this.buttonGo.Size = new System.Drawing.Size(43, 23);
			this.buttonGo.TabIndex = 2;
			this.buttonGo.Text = "Go";
			this.buttonGo.UseVisualStyleBackColor = true;
			this.buttonGo.Click += new System.EventHandler(this.buttonGo_Click);
			// 
			// statusStripMain
			// 
			this.statusStripMain.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.toolStripStatusLabelResults});
			this.statusStripMain.Location = new System.Drawing.Point(0, 124);
			this.statusStripMain.Name = "statusStripMain";
			this.statusStripMain.Size = new System.Drawing.Size(716, 22);
			this.statusStripMain.SizingGrip = false;
			this.statusStripMain.TabIndex = 4;
			// 
			// toolStripStatusLabelResults
			// 
			this.toolStripStatusLabelResults.Name = "toolStripStatusLabelResults";
			this.toolStripStatusLabelResults.Size = new System.Drawing.Size(73, 17);
			this.toolStripStatusLabelResults.Text = "processing...";
			this.toolStripStatusLabelResults.Visible = false;
			// 
			// backgroundWorkerInbox
			// 
			this.backgroundWorkerInbox.WorkerReportsProgress = true;
			// 
			// buttonClean
			// 
			this.buttonClean.Location = new System.Drawing.Point(661, 96);
			this.buttonClean.Name = "buttonClean";
			this.buttonClean.Size = new System.Drawing.Size(43, 23);
			this.buttonClean.TabIndex = 0;
			this.buttonClean.Text = "Clean";
			this.buttonClean.Click += new System.EventHandler(this.buttonClean_Click);
			// 
			// textBoxOutput
			// 
			this.textBoxOutput.Location = new System.Drawing.Point(15, 40);
			this.textBoxOutput.Multiline = true;
			this.textBoxOutput.Name = "textBoxOutput";
			this.textBoxOutput.ScrollBars = System.Windows.Forms.ScrollBars.Both;
			this.textBoxOutput.Size = new System.Drawing.Size(640, 79);
			this.textBoxOutput.TabIndex = 5;
			// 
			// FormMain
			// 
			this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
			this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
			this.ClientSize = new System.Drawing.Size(716, 146);
			this.Controls.Add(this.buttonClean);
			this.Controls.Add(this.textBoxOutput);
			this.Controls.Add(this.statusStripMain);
			this.Controls.Add(this.buttonGo);
			this.Controls.Add(this.textBoxQuery);
			this.Controls.Add(this.labelQuery);
			this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedToolWindow;
			this.Location = new System.Drawing.Point(50, 40);
			this.Name = "FormMain";
			this.StartPosition = System.Windows.Forms.FormStartPosition.Manual;
			this.Text = "A4 User Interface";
			this.statusStripMain.ResumeLayout(false);
			this.statusStripMain.PerformLayout();
			this.ResumeLayout(false);
			this.PerformLayout();

		}

		#endregion

		private System.Windows.Forms.Label labelQuery;
		private System.Windows.Forms.TextBox textBoxQuery;
		private System.Windows.Forms.Button buttonGo;
		private System.Windows.Forms.StatusStrip statusStripMain;
		private System.ComponentModel.BackgroundWorker backgroundWorkerInbox;
		private System.Windows.Forms.ToolStripStatusLabel toolStripStatusLabelResults;
		private System.Windows.Forms.Button buttonClean;
		private System.Windows.Forms.TextBox textBoxOutput;
	}
}

