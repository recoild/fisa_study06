package probono.controller;

import probono.model.dto.Beneficiary;
import probono.model.dto.Donator;
import probono.model.dto.TalentDonationProject;
import probono.service.TalentDonationProjectService;
import probono.view.EndView;
import probono.view.FailView;

public class TalentDonationProjectController {

	// singleton design pattern
	private static TalentDonationProjectController instance = new TalentDonationProjectController();

	private static TalentDonationProjectService service = TalentDonationProjectService.getInstance();

	private TalentDonationProjectController() {}

	public static TalentDonationProjectController getInstance() {
		return instance;
	}
  
	
	/**
	 * 모든 Project 검색
	 * 
	 * @return 모든 Project
	 */
	public void getDonationProjectsList() {
		EndView.projectListView(service.getDonationProjectsList());	
	}


	/**
	 * Project 이름으로 검색 - 객체된 Project 반환하기
	 * 
	 * @param projectName 프로젝트 이름
	 * @return TalentDonationProject 검색된 프로젝트
	 */
	public void getDonationProject(String projectName) {
		EndView.projectView(service.getDonationProject(projectName));
	}

	
	/**
	 * 새로운 Project 추가
	 * 
	 * @param project 저장하고자 하는 새로운 프로젝트
	 */
	public void donationProjectInsert(TalentDonationProject project){
	
		String projectName = project.getTalentDonationProjectName();
		if(projectName != null && projectName.length() != 0) {
			try {
				
				service.donationProjectInsert(project);
				EndView.successMessage("새로운 프로젝트 등록 성공했습니다.");
				
			} catch (Exception e) {
				FailView.failViewMessage(e.getMessage()); //실패인 경우 예외로 end user 서비스
				e.printStackTrace();
			}
		}else {
			FailView.failViewMessage("입력 부족, 재 확인 하세요~~");
		}
	}

	/**
	 * Project의 기부자 수정 - 프로젝트 명으로 검색해서 해당 프로젝트의 기부자 수정
	 * 
	 * @param projectName 프로젝트 이름
	 * @param people      기부자
	 */
	public void donationProjectUpdate(String projectName, Donator people) {
		
		try {
			service.donationProjectUpdate(projectName, people);
		} catch (Exception e) {
			FailView.failViewMessage(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Project의 수혜자 수정 - 프로젝트 명으로 검색해서 해당 프로젝트의 수혜자 수정
	 * 
	 * @param projectName 프로젝트 이름
	 * @param people      수혜자
	 */
	public void beneficiaryProjectUpdate(String projectName, Beneficiary people) {
		service.beneficiaryProjectUpdate(projectName, people);
	}

	/**
	 * Project 삭제 - 프로젝트 명으로 해당 프로젝트 삭제
	 * 
	 * @param projectName 삭제하고자 하는 프로젝트 이름
	 */
	public void donationProjectDelete(String projectName) {
		service.donationProjectDelete(projectName);
	}

}
