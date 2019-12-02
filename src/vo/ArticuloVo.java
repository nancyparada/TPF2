package vo;


public class ArticuloVo {		
	private Integer idCodigoArt;
	private String  nombreArt;
	private Double  precioArt;
	private Integer fabricanteCodArt;
	private Integer stockArt;

	/**
	 * @return the idCodigo
	 */
	public Integer getIdCodigoArt() {
		return idCodigoArt;
	}

	public void setIdCodigoArt(Integer idCodigoArt) {
		this.idCodigoArt = idCodigoArt;
	}
	/**
	 * @return the nombreArt
	 */
	public String getNombreArt() {
		return nombreArt;
	}

	public void setNombreArt(String nombreArt) {
		this.nombreArt = nombreArt;
	}
	/**
	 * @return the PrecioArt
	 */
	public Double getPrecioArt() {
		return precioArt;
	}

	public void setPrecioArt(Double precioArt) {
		this.precioArt = precioArt;
	}
	/**
	 * @return the FabricanteCodArt
	 */
	public Integer getFabricanteCodArt() {
		return fabricanteCodArt;
	}

	public void setFabricanteCodArt(Integer fabricanteCodArt) {
		this.fabricanteCodArt = fabricanteCodArt;
	}

	/**
	 * @return the StockArt
	 */
	public Integer getStockArt() {
		return stockArt;
	}

	public void setStockArt(Integer stockArt) {
		this.stockArt = stockArt;
	}
	
	@Override  // representacion en texto del obj
	public String toString() {
		return nombreArt;
	}

}
