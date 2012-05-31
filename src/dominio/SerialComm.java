package dominio;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.TooManyListenersException;

import modelo.Dados;
import modelo.FlagStatus;
import dominio.ComunicacaoSerialDAO;;




/*
 *
 * @author Desiree Santos
 */
public class SerialComm implements SerialPortEventListener {

	InputStream inputStream;

    Dados dado = new Dados(FlagStatus.F);
    ComunicacaoSerialDAO coDao = new ComunicacaoSerialDAO();
    

	public void execute() {
		
		dado.setDados("Grave texto de teste");
		coDao.salvar(dado);
		
		

		String portName = getPortNameByOS();
		
		CommPortIdentifier portId = getPortIdentifier(portName);		
		if (portId != null) {

			try {
				SerialPort serialPort = (SerialPort) portId.open(this
						.getClass().getName(), 2000);

				inputStream = serialPort.getInputStream();

				serialPort.addEventListener(this);
				serialPort.notifyOnDataAvailable(true);
				serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8,
						SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

			} catch (PortInUseException e) {
			}

			catch (IOException e) {
			}

			catch (UnsupportedCommOperationException e) {
				e.printStackTrace();
			} catch (TooManyListenersException e) {
			}

		} else {
			System.out.println("Porta Serial n‹o dispon’vel");
		}
	}

	
	/**
	 * Obter porta serial Multiplataforma(Windows-Linux-Mac)
	 **/
	private String getPortNameByOS() {

		String soName = System.getProperty("os.name", "").toLowerCase();
	
		if (soName.startsWith("windows")) {
			// windows
			return "COM14";
		} else if (soName.startsWith("linux")) {
			// linux
			return "/dev/ttyS0";
		} else if (soName.startsWith("mac")) {
			// mac
			return "/dev/tty.usbserial-A400AV01";
		} else {
			System.out.println("N‹o suporta o Sistema Operacional Corrente !");
			System.exit(1);
			return null;
		}

	}

	/**
	 * Identificar porta serial ativa
	 **/
	@SuppressWarnings("unused")
	private CommPortIdentifier getPortIdentifier(String portName) {
		@SuppressWarnings("rawtypes")
		Enumeration portList = CommPortIdentifier.getPortIdentifiers();
		Boolean portFound = false;
		while (portList.hasMoreElements()) {
			CommPortIdentifier portId = (CommPortIdentifier) portList
					.nextElement();
			if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
				System.out.println("Available port: " + portId.getName());
				if (portId.getName().equals(portName)) {
					System.out.println("Found port: " + portName);
					portFound = true;
					return portId;
				}
			}

		}

		return null;

	}

	/**
	 * Ler dados da porta serial 
	 **/
	public void serialEvent(SerialPortEvent event) {
		
		

		switch ( event.getEventType() ) {
		case SerialPortEvent.BI:
		case SerialPortEvent.OE:
		case SerialPortEvent.FE:
		case SerialPortEvent.PE:
		case SerialPortEvent.CD:
		case SerialPortEvent.CTS:
		case SerialPortEvent.DSR:
		case SerialPortEvent.RI:
		case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
			break;
		case SerialPortEvent.DATA_AVAILABLE:
			
			
		    
			byte[] readBuffer = new byte[20];
			try {
				int numBytes = 0;
				while (inputStream.available() > 0) {
					numBytes = inputStream.read(readBuffer);
				}
				String result = new String(readBuffer);
				result = result.substring(1, numBytes);
				
				
				System.out.println("Read: " + result);
				

			} catch (IOException e) {
			}

			break;
		}
	}

}