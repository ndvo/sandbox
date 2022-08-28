module ExpenseTracker
	RecordResult = Struct.new(:sucess?, :expense_id, :error_message)

	class Ledger
		def record(expense)
		end
	end
end
