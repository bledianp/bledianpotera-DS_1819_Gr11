namespace Implementimi_I_BASE32_Enkoderit_Dekoderit
{
    partial class Form1
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
            this.btnDecode = new System.Windows.Forms.Button();
            this.label3 = new System.Windows.Forms.Label();
            this.txtDecodedText = new System.Windows.Forms.TextBox();
            this.txtEncodedtext = new System.Windows.Forms.TextBox();
            this.txtPlaintext = new System.Windows.Forms.TextBox();
            this.btnEncode = new System.Windows.Forms.Button();
            this.label2 = new System.Windows.Forms.Label();
            this.label1 = new System.Windows.Forms.Label();
            this.SuspendLayout();
            // 
            // btnDecode
            // 
            this.btnDecode.Location = new System.Drawing.Point(505, 245);
            this.btnDecode.Name = "btnDecode";
            this.btnDecode.Size = new System.Drawing.Size(108, 35);
            this.btnDecode.TabIndex = 15;
            this.btnDecode.Text = "Decode";
            this.btnDecode.UseVisualStyleBackColor = true;
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(46, 301);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(96, 17);
            this.label3.TabIndex = 14;
            this.label3.Text = "Decoded Text";
            // 
            // txtDecodedText
            // 
            this.txtDecodedText.Location = new System.Drawing.Point(148, 297);
            this.txtDecodedText.Name = "txtDecodedText";
            this.txtDecodedText.Size = new System.Drawing.Size(465, 22);
            this.txtDecodedText.TabIndex = 13;
            // 
            // txtEncodedtext
            // 
            this.txtEncodedtext.Location = new System.Drawing.Point(148, 180);
            this.txtEncodedtext.MinimumSize = new System.Drawing.Size(270, 22);
            this.txtEncodedtext.Name = "txtEncodedtext";
            this.txtEncodedtext.Size = new System.Drawing.Size(465, 22);
            this.txtEncodedtext.TabIndex = 12;
            // 
            // txtPlaintext
            // 
            this.txtPlaintext.Location = new System.Drawing.Point(148, 80);
            this.txtPlaintext.Name = "txtPlaintext";
            this.txtPlaintext.Size = new System.Drawing.Size(465, 22);
            this.txtPlaintext.TabIndex = 11;
            // 
            // btnEncode
            // 
            this.btnEncode.Location = new System.Drawing.Point(505, 124);
            this.btnEncode.Name = "btnEncode";
            this.btnEncode.Size = new System.Drawing.Size(108, 35);
            this.btnEncode.TabIndex = 10;
            this.btnEncode.Text = "Encode";
            this.btnEncode.UseVisualStyleBackColor = true;
            this.btnEncode.Click += new System.EventHandler(this.btnEncode_Click);
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(43, 180);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(95, 17);
            this.label2.TabIndex = 9;
            this.label2.Text = "Encoded Text";
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(43, 80);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(61, 17);
            this.label1.TabIndex = 8;
            this.label1.Text = "Plaintext";
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(723, 404);
            this.Controls.Add(this.btnDecode);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.txtDecodedText);
            this.Controls.Add(this.txtEncodedtext);
            this.Controls.Add(this.txtPlaintext);
            this.Controls.Add(this.btnEncode);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Name = "Form1";
            this.Text = "Base32 Enkoderi/Dekoderi";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button btnDecode;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.TextBox txtDecodedText;
        private System.Windows.Forms.TextBox txtEncodedtext;
        private System.Windows.Forms.TextBox txtPlaintext;
        private System.Windows.Forms.Button btnEncode;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label1;
    }
}

