package channel.payment.biz;
import channel.payment.dao.PaymentDao;
import channel.payment.dto.PaymentDto;


public class PaymentBiz {

	PaymentDao dao = new PaymentDao();
	
	public int paymentres(PaymentDto dto) {
			
		return dao.paymentres(dto);
	}

}
