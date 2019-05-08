using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;


namespace Implementimi_I_BASE32_Enkoderit_Dekoderit
{
    public partial class Form1 : Form
    {

        public byte[] inputibytes;
        public string inputi;
        public string TekstiEnkoduar;
        public string TekstiDekoduar;
        private const int MadhesiaBajtit = 8;
        private const int BllokuDales = 5;
        private const string AlfabetiBASE32 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567";

        public Form1()
        {
            InitializeComponent();
            MessageBox.Show("KUJDES!\n-Per dekodim hiqni karakteret qe perdoren per padding '='\n-Paddingu nuk ndryshon permbajtjen\n");
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            
        }

        private void btnEncode_Click(object sender, EventArgs e)

        {
            inputi = txtPlaintext.Text;
            inputibytes = Encoding.ASCII.GetBytes(inputi.ToString());
            TekstiEnkoduar = Enkodo(inputibytes);
            txtEncodedtext.Text = TekstiEnkoduar;

        }
        static string Enkodo(byte[] bytes)
        {

            if (bytes == null)
            {
                return null;
            }

            else if (bytes.Length == 0)
            {
                return string.Empty;
            }


            StringBuilder builder = new StringBuilder(bytes.Length * MadhesiaBajtit / BllokuDales);


            int bytesPosition = 0;



            int bytesSubPosition = 0;


            byte outputBase32Byte = 0;


            int outputBase32BytePosition = 0;


            while (bytesPosition < bytes.Length)
            {

                int bitsAvailableInByte = Math.Min(MadhesiaBajtit - bytesSubPosition, BllokuDales - outputBase32BytePosition);


                outputBase32Byte <<= bitsAvailableInByte;


                outputBase32Byte |= (byte)(bytes[bytesPosition] >> (MadhesiaBajtit - (bytesSubPosition + bitsAvailableInByte)));


                bytesSubPosition += bitsAvailableInByte;


                if (bytesSubPosition >= MadhesiaBajtit)
                {

                    bytesPosition++;
                    bytesSubPosition = 0;
                }


                outputBase32BytePosition += bitsAvailableInByte;


                if (outputBase32BytePosition >= BllokuDales)
                {

                    outputBase32Byte &= 0x1F;


                    builder.Append(AlfabetiBASE32[outputBase32Byte]);


                    outputBase32BytePosition = 0;
                }
            }


            if (outputBase32BytePosition > 0)
            {

                outputBase32Byte <<= (BllokuDales - outputBase32BytePosition);


                outputBase32Byte &= 0x1F;


                builder.Append(AlfabetiBASE32[outputBase32Byte]);
            }

            if ((bytes.Length * MadhesiaBajtit) % BllokuDales == 0)
            {
                return builder.ToString();
            }


            else if (bytes.Length < 5)
            {
                int paddingcount = Math.Abs((int)((bytes.Length * MadhesiaBajtit) / BllokuDales) - 8 + 1);

                List<string> list = new List<string>();
                for (int i = 0; i < paddingcount; i++)
                {
                    list.Add("=");
                }

                string paddingString = string.Join("", list.ToArray());
                return builder.ToString() + paddingString;

            }
            else if (bytes.Length > 5 && bytes.Length < 10)
            {
                int paddingcount = Math.Abs((int)((bytes.Length * MadhesiaBajtit) / BllokuDales) - 16 + 1);

                List<string> list = new List<string>();
                for (int i = 0; i < paddingcount; i++)
                {
                    list.Add("=");
                }

                string paddingString = string.Join("", list.ToArray());
                return builder.ToString() + paddingString;
            }



            else
            {
                return builder.ToString();
            }



        }


 private void btnDecode_Click(object sender, EventArgs e)
        {
            inputi = txtEncodedtext.Text;
            TekstiDekoduar = Encoding.ASCII.GetString(Dekodo(inputi));
            txtDecodedtext.Text = TekstiDekoduar;

        }
            
        static byte[] Dekodo(string base32String)
        {
           
            if (base32String == null)
            {
                return null;
            }
          
            else if (base32String == string.Empty)
            {
                return new byte[0];
            }

            
            string base32StringUpperCase = base32String.ToUpperInvariant();

            
            byte[] outputBytes = new byte[base32StringUpperCase.Length * BllokuDales / MadhesiaBajtit];

            
            if (outputBytes.Length == 0)
            {
                throw new ArgumentException("Teksti nuk eshte valid sepse nuk ka te dhena per te konstruktuar bytearray");
            }
            int base32Position = 0;

            int base32SubPosition = 0;
            int outputBytePosition = 0;
            int outputByteSubPosition = 0;

            
            while (outputBytePosition < outputBytes.Length)
            {
                
                int currentBase32Byte = AlfabetiBASE32.IndexOf(base32StringUpperCase[base32Position]);

               
                if (currentBase32Byte < 0)
                {
                    throw new ArgumentException(string.Format("Teksti i dhene nuk eshte valid per Base32", base32String[base32Position]));
                }
                int bitsAvailableInByte = Math.Min(BllokuDales - base32SubPosition, MadhesiaBajtit - outputByteSubPosition);

               
                outputBytes[outputBytePosition] <<= bitsAvailableInByte;

                
                outputBytes[outputBytePosition] |= (byte)(currentBase32Byte >> (BllokuDales - (base32SubPosition + bitsAvailableInByte)));
                outputByteSubPosition += bitsAvailableInByte;
                if (outputByteSubPosition >= MadhesiaBajtit)
                {
                    outputBytePosition++;
                    outputByteSubPosition = 0;
                }

                
                base32SubPosition += bitsAvailableInByte;
                if (base32SubPosition >= BllokuDales)
                {                  
                    base32Position++;
                    base32SubPosition = 0;
                }
            }

            return outputBytes;
        }




    }
}
